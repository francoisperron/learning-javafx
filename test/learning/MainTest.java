package learning;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest extends ApplicationTest
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("main.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @BeforeClass
    public static void headless()
    {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown() throws Exception
    {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void labelTextChangesToInputTextOnButtonClick()
    {
        clickOn("#inputField");
        write("hello");
        clickOn("#applyButton");

        Label label = find("#label");
        assertThat(label.getText(), is("hello"));
    }

    private <T extends Node> T find(String query)
    {
        return lookup(query).query();
    }
}
