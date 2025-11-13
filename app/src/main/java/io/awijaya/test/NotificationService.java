package io.awijaya.test;

import java.util.*;

import static io.awijaya.test.NotificationService.EventStatus.COMPLETED;

/**
 * You are working on a deployment notification service for keeping developers informed about when their changes have been deployed.
 * The service should provide two interfaces, one to receive an event and another to send out a batch of notifications for any received events.
 * An event contains a version number, a list of authors, and a status indicating if the deployment has started, completed, or failed.
 * Deployment notifications should include the author and the version in which their changes were deployed.
 * <p>
 * The goal is to notify code change authors on the first time their changes are deployed successfully for each unique set of code changes.
 */
public class NotificationService {
    public static final class Event {
        private int versionNumber;
        private Set<String> authors;
        private EventStatus status;
    }

    public enum EventStatus {
        STARTED("started"), COMPLETED("completed"), FAILED("failed");
        private String status;

        EventStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static final class Notification {
        private String author;
        private int versionNumber;

        public Notification(String author, int versionNumber) {
            this.author = author;
            this.versionNumber = versionNumber;
        }
    }

    public interface NotificationServiceInterface {
        void onReceive(Event event);

        List<Notification> sendSuccessfulNotifications();
    }

    public class NotificationServiceImpl implements NotificationServiceInterface {
        Deque<Event> successfulEvents;

        public NotificationServiceImpl() {
            successfulEvents = new ArrayDeque<>();
        }

        @Override
        public void onReceive(Event event) {
            if (event != null && event.status.equals(COMPLETED)) {
                successfulEvents.add(event);
            }
        }

        @Override
        public List<Notification> sendSuccessfulNotifications() {
            List<Notification> sentNotifications = new ArrayList<>();
            while (!successfulEvents.isEmpty()) {
                Event successfulEvent = successfulEvents.poll();

                for(String author: successfulEvent.authors) {
                    Notification notification = new Notification(author, successfulEvent.versionNumber);
                    sentNotifications.add(notification);
                }
            }
            return sentNotifications;
        }
    }

    public static void main(String[] args) {
        Event event = new Event();
        event.status = COMPLETED;

        if(event.status.equals(COMPLETED)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
