package org.front;
//====================================================================================
//PIXELTAIL-AutoLaunch-App
//为像素尾巴启动器提供前端
//====================================================================================
//------------------------------------------------------------------------------------
//导入必要的依赖
//------------------------------------------------------------------------------------
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//------------------------------------------------------------------------------------
//声明App公共类
//------------------------------------------------------------------------------------
public class App extends Application {
    //--------------------------------------------------------------------------------
    //重写start方法
    //--------------------------------------------------------------------------------
    @Override
    public void start(Stage stage) throws Exception {
        //----------------------------------------------------------------------------
        //配置窗口控件
        //----------------------------------------------------------------------------
        //添加标签
        Label label = new Label("PIXELTAIL一键启动");
        //配置布局并加入控件
        BorderPane pane = new BorderPane(label);
        //创建场景并加入布局
        Scene scene = new Scene(pane, 854, 480);
        //----------------------------------------------------------------------------
        //配置窗口设置
        //----------------------------------------------------------------------------
        //设置标题
        stage.setTitle("PIXELTAIL一键启动");
        //设置场景
        stage.setScene(scene);
        //----------------------------------------------------------------------------
        //展示窗口
        //----------------------------------------------------------------------------
        stage.show();
    }
}