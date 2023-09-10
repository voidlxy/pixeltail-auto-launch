package org.front;
//====================================================================================
//PIXELTAIL-AutoLaunch-AppLaunch
//为像素尾巴启动器前端提供控制器
//====================================================================================
//------------------------------------------------------------------------------------
//导入必要的依赖
//------------------------------------------------------------------------------------
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        //定义按钮事件
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
}

