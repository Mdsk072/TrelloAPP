import entity.Board;
import entity.User;
import enums.Privacy;
import repository.BoardRepository;
import repository.UserRepository;
import service.*;

import java.util.Scanner;

public class Main {
    private static final UserRepository userRepo = new UserRepository();
    private static final BoardRepository boardRepo = new BoardRepository();

    private static final UserService userService = new UserService(userRepo);
    private static final BoardService boardService = new BoardService(boardRepo);
    private static final ListService listService = new ListService(boardRepo);
    private static final CardService cardService = new CardService(boardRepo);
    private static final BookmarkService bookmarkService = new BookmarkService(userRepo, boardRepo);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter user ID:");
                    String userId = scanner.nextLine();
                    System.out.println("Enter user name:");
                    String userName = scanner.nextLine();
                    System.out.println("Enter user email:");
                    String userEmail = scanner.nextLine();
                    userService.createUser(userId, userName, userEmail);
                    System.out.println("User created!");
                    break;
                case 2:
                    System.out.println("Enter board ID:");
                    String boardId = scanner.nextLine();
                    System.out.println("Enter board name:");
                    String boardName = scanner.nextLine();
                    boardService.createBoard(boardId, boardName);
                    System.out.println("Board created!");
                    break;
                case 3:
                    System.out.println("Enter board ID where the list is to be added:");
                    String boardIdForList = scanner.nextLine();
                    System.out.println("Enter list ID:");
                    String listId = scanner.nextLine();
                    System.out.println("Enter list name:");
                    String listName = scanner.nextLine();
                    listService.createList(boardIdForList, listId, listName);
                    System.out.println("List added to board!");
                    break;
                case 4:
                    System.out.println("Enter board ID and list ID where card is to be added (format: boardID listID):");
                    String boardIdForCard = scanner.next();
                    String listIdForCard = scanner.next();
                    scanner.nextLine();
                    System.out.println("Enter card ID:");
                    String cardId = scanner.nextLine();
                    System.out.println("Enter card name:");
                    String cardName = scanner.nextLine();
                    System.out.println("Enter card description:");
                    String cardDesc = scanner.nextLine();
                    System.out.println("Enter card priority (numeric):");
                    int cardPriority = scanner.nextInt();
                    scanner.nextLine();
                    cardService.createCard(boardIdForCard, listIdForCard, cardId, cardName, cardDesc, cardPriority);
                    System.out.println("Card added to list!");
                    break;
                case 5:
                    System.out.println("Existing boards: " + boardService.getAllBoards());
                    break;
                case 6:
                    System.out.print("Enter board ID to view: ");
                    String boardIdToView = scanner.nextLine();
                    System.out.println(boardService.getBoard(boardIdToView));
                    break;
                case 7:
                    System.out.print("Enter user ID: ");
                    String userIdForBookmark = scanner.nextLine();
                    System.out.print("Enter board/list/card ID to bookmark: ");
                    String entityIdForBookmark = scanner.nextLine();
                    bookmarkService.bookmarkBoard(userIdForBookmark, entityIdForBookmark);
                    System.out.println("Entity bookmarked!");
                    break;
                case 8:
                    System.out.print("Enter user ID to view bookmarks: ");
                    String userIdForViewing = scanner.nextLine();
                    System.out.println("Bookmarked Boards: " + bookmarkService.getUserBookmarkedBoards(userIdForViewing));
                    System.out.println("Bookmarked Lists: " + bookmarkService.getUserBookmarkedLists(userIdForViewing));
                    System.out.println("Bookmarked Cards: " + bookmarkService.getUserBookmarkedCards(userIdForViewing));
                    break;
                case 9:
                    System.out.println("Enter board ID to add a member:");
                    String boardIdForAddingMember = scanner.nextLine();

                    System.out.println("Enter user ID of the member to add:");
                    String userIdForAdding = scanner.nextLine();

                    User userToAdd = userService.findUserById(userIdForAdding);
                    if (userToAdd == null) {
                        System.out.println("User not found!");
                        break;
                    }

                    boardService.addMemberToBoard(boardIdForAddingMember, userToAdd);
                    System.out.println("Member added to board!");
                    break;
                case 10:
                    System.out.println("Enter board ID to remove a member:");
                    String boardIdForRemovingMember = scanner.nextLine();

                    System.out.println("Enter user ID of the member to remove:");
                    String userIdForRemoving = scanner.nextLine();

                    User userToRemove = userService.findUserById(userIdForRemoving);
                    if (userToRemove == null) {
                        System.out.println("User not found!");
                        break;
                    }

                    boardService.removeMemberFromBoard(boardIdForRemovingMember, userToRemove);
                    System.out.println("Member removed from board!");
                    break;
                case 11:
                    System.out.println("Enter board ID to modify:");
                    String boardIdForModifying = scanner.nextLine();

                    Board boardToModify = boardService.findBoardById(boardIdForModifying);
                    if (boardToModify == null) {
                        System.out.println("Board not found!");
                        break;
                    }
                    System.out.println("Enter new privacy setting (PUBLIC or PRIVATE):");
                    String privacyInput = scanner.nextLine();
                    Privacy newPrivacy;
                    if ("PUBLIC".equalsIgnoreCase(privacyInput)) {
                        newPrivacy = Privacy.PUBLIC;
                    } else if ("PRIVATE".equalsIgnoreCase(privacyInput)) {
                        newPrivacy = Privacy.PRIVATE;
                    } else {
                        System.out.println("Invalid privacy input. Keeping the current setting.");
                        newPrivacy = boardToModify.getPrivacy();
                    }

                    String newBoardName = null;
                    boardService.modifyBoard(boardToModify, newBoardName, newPrivacy);
                    System.out.println("Board modified!");
                    break;

                case 12:
                    System.out.println("Enter board ID to delete:");
                    String boardIdForDeleting = scanner.nextLine();
                    boardService.deleteBoard(boardIdForDeleting);
                    System.out.println("Board deleted!");
                    break;
                case 13:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option!");
            }

        }
    }
    private static void showMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Create User");
        System.out.println("2. Create Board");
        System.out.println("3. Add List to Board");
        System.out.println("4. Add Card to List");
        System.out.println("5. View All Boards");
        System.out.println("6. View a Board");
        System.out.println("7. Bookmark an entity (Board/List/Card)");
        System.out.println("8. View Bookmarked entities of a user");
        System.out.println("9. Add Member to a Board");
        System.out.println("10. Remove Member from a Board");
        System.out.println("11. Modify Board");
        System.out.println("12. Delete Board");
        System.out.println("13. Exit");
        System.out.print("Your choice: ");
    }
}