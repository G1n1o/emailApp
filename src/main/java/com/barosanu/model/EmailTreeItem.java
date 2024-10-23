package com.barosanu.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;

import javax.activation.MimeType;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailTreeItem<String> extends TreeItem<String> {

    private String name;
    private ObservableList<EmailMessage> emailMessages;
    private int unreadMessangesCount;

    public EmailTreeItem(String name) {
        super(name);
        this.name = name;
        this.emailMessages = FXCollections.observableArrayList();
    }
    public ObservableList<EmailMessage> getEmailMessages(){
      return  emailMessages;
    }

    public void addEmail(Message message) throws MessagingException {
        EmailMessage emailMessage = fetchMessage(message);
        emailMessages.add(emailMessage);
    }
    public void addEmailToTop(Message message) throws MessagingException {
        EmailMessage emailMessage = fetchMessage(message);
        emailMessages.addFirst(emailMessage);
    }

    private EmailMessage fetchMessage(Message message) throws MessagingException {
        boolean messageIsRead = message.getFlags().contains(Flags.Flag.SEEN);

        String fromEncoded = (String) message.getFrom()[0].toString();
        String fromDecoded = null;
        try {
            fromDecoded = (String) MimeUtility.decodeText((java.lang.String) fromEncoded);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Failed to decode sender's name");
            fromDecoded = fromEncoded; //
        }


        EmailMessage emailMessage = new EmailMessage(
                message.getSubject(),
                (java.lang.String) fromDecoded,
                message.getRecipients(MimeMessage.RecipientType.TO)[0].toString(),
                message.getSize(),
                message.getSentDate(),
                messageIsRead,
                message
        );

        if (!messageIsRead) {
            incrementMessagesCount();
        }
        return emailMessage;
    }

    public void incrementMessagesCount() {
        unreadMessangesCount++;
        updateName();
    }


    private void updateName() {
        if (unreadMessangesCount > 0) {
            this.setValue((String) (name + "(" + unreadMessangesCount + ")"));
        } else {
            this.setValue(name);
        }
    }


    public void decrementMessagesCount() {
        unreadMessangesCount--;
        updateName();
    }
}
