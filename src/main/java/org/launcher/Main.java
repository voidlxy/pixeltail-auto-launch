package org.launcher;
//====================================================================================
//PixelTail-AutoLaunch
//为像素尾巴新人制作的一键启动
//====================================================================================
//------------------------------------------------------------------------------------
//导入必要的依赖
//------------------------------------------------------------------------------------
//导入外置登录模块
import org.to2mbn.jmccc.auth.yggdrasil.DefaultCharacterSelector;
import org.to2mbn.jmccc.auth.yggdrasil.YggdrasilAuthenticator;
import org.to2mbn.jmccc.auth.yggdrasil.core.AuthenticationService;
import org.to2mbn.jmccc.auth.yggdrasil.core.yggdrasil.YggdrasilAuthenticationServiceBuilder;
//导入启动模块
import org.to2mbn.jmccc.launch.LaunchException;
import org.to2mbn.jmccc.launch.Launcher;
import org.to2mbn.jmccc.launch.LauncherBuilder;
import org.to2mbn.jmccc.launch.ProcessListener;
import org.to2mbn.jmccc.option.JavaEnvironment;
import org.to2mbn.jmccc.option.LaunchOption;
import org.to2mbn.jmccc.option.MinecraftDirectory;
import org.to2mbn.jmccc.option.WindowSize;
//导入Java组件
import java.io.File;
import java.io.IOException;

//------------------------------------------------------------------------------------
//声明Main公共类
//------------------------------------------------------------------------------------
public class Main {
    //--------------------------------------------------------------------------------
    //定义mcLaunch方法
    //--------------------------------------------------------------------------------
    public static void mcLaunch(String UserName, String PassWord, String Version, String RootDir, int MaxMemory, int Width, int Height, String JreDir, String McRuntimeDir, String JavaAgent) throws IOException, LaunchException {
        //----------------------------------------------------------------------------
        //构建启动容器
        //----------------------------------------------------------------------------
        Launcher lau = LauncherBuilder.create()//构建启动器
                .printDebugCommandline(true)//开启命令行显示
                .nativeFastCheck(false)//关闭快速检查
                .build();
        //----------------------------------------------------------------------------
        //配置外置服务器
        //----------------------------------------------------------------------------
        AuthenticationService aut = YggdrasilAuthenticationServiceBuilder.create()
                .apiProvider(new PixelTailAPI())//从PixelTailAPI类加载外置API
                .build();//构建外置API供应商
        YggdrasilAuthenticator ygg = new YggdrasilAuthenticator(aut);//实例化API
        ygg.refreshWithPassword(
                UserName,
                PassWord,
                new DefaultCharacterSelector()//创建角色选择器
        );
        //----------------------------------------------------------------------------
        //配置启动器设置
        //----------------------------------------------------------------------------
        LaunchOption opt = new LaunchOption(
                Version,//此处为MC的版本号
                ygg,//调用ygg中的账户信息
                new MinecraftDirectory(RootDir)//此处为游戏目录
        );
        //设置最大内存
        opt.setMaxMemory(MaxMemory);
        //设置窗口大小
        opt.setWindowSize(
                new WindowSize(Width,Height)
        );
        //设置运行环境
        opt.setJavaEnvironment(
                new JavaEnvironment(
                        new File(JreDir)
                )
        );
        //设置游戏运行目录
        opt.setRuntimeDirectory(
                new MinecraftDirectory(McRuntimeDir)
        );
        //设置额外参数
        opt.extraJvmArguments().add("-javaagent:" + JavaAgent + "=https://skin.pixeltail.cn/api/yggdrasil/");
        //----------------------------------------------------------------------------
        //启动游戏并输出日志
        //----------------------------------------------------------------------------
        lau.launch(opt/*通过之前的启动参数启动游戏*/, new ProcessListener()/*控制台输出游戏日志*/ {
            @Override
            public void onLog(String s) {
                //输出日志
                System.out.println(s);
            }

            @Override
            public void onErrorLog(String s) {
                //输出错误日志
                System.out.println(s);
            }

            @Override
            public void onExit(int i) {
                //输出状态码
                System.out.println("状态码："+i);
            }
        });
    }
}