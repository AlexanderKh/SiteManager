package alex.interaction;

import alex.entity.Page;
import alex.entity.Permission;
import alex.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Controller
public class PageEditInteraction {
    private Page page;

    @Autowired
    private PageService pageService;
    @Autowired
    private InteractionHelper helper;
    private Scanner in;
    private PrintStream out;

    PageEditInteraction(){
        in = IOContainer.in;
        out = IOContainer.out;
    }

    public void edit() {
        int ans = 0;
        boolean exit = false;
        while (!exit){
            out.println("-----------------------");
            out.println("Page id: " + page.getId() +
                    "\tTitle: " + page.getTitle() +
                    "\tOwner: " + page.getAuthor().getName() +
                    "\tPermission level: " + page.getPermission());
            out.println("-----------------------");
            out.println("1 - Print page content");
            out.println("2 - Change title");
            out.println("3 - Change contents");
            out.println("4 - Change permission level");
            out.println("5 - Delete page");
            out.println("0 - End editing");
            out.println("-----------------------");
            ans = helper.getIntFromUser();

            switch (ans) {
                case 1:
                    out.println(page.getContent());
                    break;
                case 2:
                    changeTitle();
                    break;
                case 3:
                    changeContents();
                    break;
                case 4:
                    changePermissionLevel();
                    break;
                case 5:
                    deletePage();
                    exit = true;
                    break;
                case 0:
                    exit = true;
                    break;
            }

        }
    }

    private void deletePage() {
        out.print("Really? (y/n): ");
        String input = in.next();
        if (input.equals("y")){
            pageService.deletePage(page);
            out.println("Page deleted");
        }
    }

    private void changePermissionLevel() {
        out.println("Enter permission level, possible vars are: ");
        for (Permission permission : Permission.values()){
            out.println(permission.toString());
        }
        String permissionInput = in.next();
        Permission permission = Permission.valueOf(permissionInput);
        pageService.changePermissionLevel(page, permission);
    }

    private void changeContents() {
        out.println("Write new content in here: ");
        out.println();
        String content = "";
        do{
            content += in.next() + in.nextLine() + "\n";
        } while (!in.nextLine().equals(""));
        pageService.setPageContent(page, content);
    }

    private void changeTitle() {
        out.print("Enter new title: ");
        String input = in.nextLine();
        pageService.changePageName(page, input);
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
