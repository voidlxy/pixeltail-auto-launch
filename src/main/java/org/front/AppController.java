package org.front;
//====================================================================================
//PIXELTAIL-AutoLaunch-AppLaunch
//为像素尾巴启动器前端提供控制器
//====================================================================================
//------------------------------------------------------------------------------------
//导入必要的依赖
//------------------------------------------------------------------------------------
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.launcher.Main;
import org.to2mbn.jmccc.launch.LaunchException;
import java.io.IOException;
//------------------------------------------------------------------------------------
//声明AppController公共类
//------------------------------------------------------------------------------------
public class AppController {
    //--------------------------------------------------------------------------------
    //为控件创建字段
    //--------------------------------------------------------------------------------
    public TextField UserName;
    public PasswordField PassWord;
    public TextField Version;
    public TextField RootDir;
    public TextField MaxMemory;
    public TextField Width;
    public TextField Height;
    public TextField JreDir;
    public TextField McRuntimeDir;
    public TextField JavaAgent;

    @FXML
    protected void onHelloButtonClick() throws LaunchException, IOException {
        //--------------------------------------------------------------------------------
        //定义启动按钮事件
        //--------------------------------------------------------------------------------
        //获取输入的内容
        String username = UserName.getText();
        String password = PassWord.getText();
        String version = Version.getText();
        String rootdir = RootDir.getText();
        int maxmemory = Integer.parseInt(MaxMemory.getText());
        int width = Integer.parseInt(Width.getText());
        int height = Integer.parseInt(Height.getText());
        String jredir = JreDir.getText();
        String mcruntimedir = McRuntimeDir.getText();
        String javaagent = JavaAgent.getText();
        //Minecraft，启动！
        Main.mcLaunch(username, password, version, rootdir, maxmemory, width, height, jredir, mcruntimedir, javaagent);
    }

    @FXML
    protected void onHelpButtonClick() throws IOException {
        //--------------------------------------------------------------------------------
        //定义帮助按钮事件
        //--------------------------------------------------------------------------------
        //创建帮助场景
        Stage helpStage = new Stage();
        //配置fxml加载器并从helpFront.xml加载控件布局
        FXMLLoader helpFxmlLoader = new FXMLLoader(AppController.class.getResource("helpFront.fxml"));
        //创建场景并加入布局
        Scene helpScene = new Scene(helpFxmlLoader.load(), 854, 480);
        //----------------------------------------------------------------------------
        //配置窗口设置
        //----------------------------------------------------------------------------
        //设置标题
        helpStage.setTitle("PIXELTAIL一键启动 - 帮助");
        //设置场景
        helpStage.setScene(helpScene);
        //----------------------------------------------------------------------------
        //展示窗口
        //----------------------------------------------------------------------------
        helpStage.show();
    }
}

