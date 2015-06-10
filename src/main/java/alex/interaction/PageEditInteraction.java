package alex.interaction;

import alex.entity.Page;
import alex.entity.PermissionType;
import alex.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
                    "\tTitle: " + page.getTitle());
            out.println("-----------------------");
            out.println("1 - Print page content");
            out.println("2 - Change title");
            out.println("3 - Change contents");
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

    private void changeContents() {
        out.println("Write new content in here: ");
        out.println();
        String content = "";
        String line = "";
        do{
            line = in.nextLine();
            content += line + "\n";
        } while (!line.equals(""));
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
