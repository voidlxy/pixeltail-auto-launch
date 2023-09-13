package org.front;
//====================================================================================
//PIXELTAIL-AutoLaunch-AppController
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
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.Map;
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
        Scene helpScene = new Scene(helpFxmlLoader.load(), 570, 320);
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

    public void initialize() throws IOException {
        //----------------------------------------------------------------------------
        //重写初始化方法，读配置文件
        //----------------------------------------------------------------------------
        //实例化File对象
        File configFile = new File("config.yml");
        //----------------------------------------------------------------------------
        //判断配置文件是否存在
        //----------------------------------------------------------------------------
        if(configFile.exists()){
            //如果存在，直接开始读取
            // 创建一个YAML对象
            Yaml yaml = new Yaml();
            // 使用FileInputStream加载YAML文件
            InputStream inputStream = new FileInputStream("config.yml");
            // 解析YAML文件内容为Map对象
            Map<String, Object> data = yaml.load(inputStream);
            //------------------------------------------------------------------------
            //判断配置文件是否为空
            //------------------------------------------------------------------------
            if (data == null || data.isEmpty()) {
                //若为空，报错并启动帮助页面
                System.out.println("Data is empty");
                onHelpButtonClick();
            }else{
                //若不空，解析yaml文件
                String userName = (String) data.get("userName");
                String passWord = (String)data.get("passWord");
                String gameVersion = (String) data.get("gameVersion");
                String rootDir = (String)data.get("rootDir");
                int maxMemory = (int) data.get("maxMemory");
                int windowWidth = (int) data.get("windowWidth");
                int windowHeight = (int) data.get("windowHeight");
                String jreDir = (String)data.get("jreDir");
                String mcRuntimeDir = (String) data.get("mcRuntimeDir");
                String javaAgent = (String)data.get("javaAgent");
                //将yaml配置文件内容显示到输入框
                UserName.setText(userName);
                PassWord.setText(passWord);
                Version.setText(gameVersion);
                RootDir.setText(rootDir);
                MaxMemory.setText(String.valueOf(maxMemory));
                Width.setText(String.valueOf(windowWidth));
                Height.setText(String.valueOf(windowHeight));
                JreDir.setText(jreDir);
                McRuntimeDir.setText(mcRuntimeDir);
                JavaAgent.setText(javaAgent);
            }
        }else{
            //若不存在，启动帮助页面
            onHelpButtonClick();
        }
    }
}