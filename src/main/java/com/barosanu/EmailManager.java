package com.barosanu;

import com.barosanu.controller.services.FetchFolderService;
import com.barosanu.model.EmailAccount;
import com.barosanu.model.EmailTreeItem;

public class EmailManager {
    //Folder handling
    private EmailTreeItem<String> foldersRoot  = new EmailTreeItem<>("");

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount){
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        FetchFolderService fetchFolderService = new FetchFolderService(emailAccount.getStore(), treeItem);
        fetchFolderService.start();

//        treeItem.setExpanded(true);
//        treeItem.getChildren().add(new TreeItem<String>("INBOX"));
//        treeItem.getChildren().add(new TreeItem<String>("Sent"));
//        treeItem.getChildren().add(new TreeItem<String>("Folder1"));
//        treeItem.getChildren().add(new TreeItem<String>("Spam"));
        foldersRoot.getChildren().add(treeItem);
    };
}
