package alex;

import alex.interaction.Interaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Run {

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext("alex/config");
        Interaction interaction = (Interaction) context.getBean(Interaction.class);
        interaction.menu();
        interaction.toString();
    }
}
