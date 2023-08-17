package entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Card {
    private String cardId;
    private String name;
    private String description;
    private User assignedUser;
    private int priority;
    private LocalDateTime createdTime;
    private LocalDateTime finishedTime;
    private LocalDateTime ETA;
    private String status;

    public void assignUser(User user) {
        this.assignedUser = user;
    }

    public void unassignUser() {
        this.assignedUser = null;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public Card(String cardId, String name, String description) {
        this.cardId = cardId;
        this.name = name;
        this.description = description;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(LocalDateTime finishedTime) {
        this.finishedTime = finishedTime;
    }

    public LocalDateTime getETA() {
        return ETA;
    }

    public void setETA(LocalDateTime ETA) {
        this.ETA = ETA;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
