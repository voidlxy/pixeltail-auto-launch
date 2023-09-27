package org.launcher;
//====================================================================================
//PixelTail-AutoLaunch-PixelTailAPI
//为像素尾巴启动器提供外置API
//====================================================================================
//------------------------------------------------------------------------------------
//导入必要的依赖
//------------------------------------------------------------------------------------
import java.util.UUID;
import org.to2mbn.jmccc.auth.yggdrasil.core.yggdrasil.YggdrasilAPIProvider;
import org.to2mbn.jmccc.util.UUIDUtils;
//------------------------------------------------------------------------------------
//声明PT_API公共类
//------------------------------------------------------------------------------------
public class PixelTailAPI implements YggdrasilAPIProvider {

    @Override
    public String authenticate() {
        //----------------------------------------------------------------------------
        //登录API
        //----------------------------------------------------------------------------
        return "https://skin.pixeltail.cn/api/yggdrasil/authserver/authenticate";
    }

    @Override
    public String refresh() {
        //----------------------------------------------------------------------------
        //刷新API
        //----------------------------------------------------------------------------
        return "https://skin.pixeltail.cn/api/yggdrasil/authserver/refresh";
    }

    @Override
    public String validate() {
        //----------------------------------------------------------------------------
        //验证令牌API
        //----------------------------------------------------------------------------
        return "https://skin.pixeltail.cn/api/yggdrasil/authserver/validate";
    }

    @Override
    public String invalidate() {
        //----------------------------------------------------------------------------
        //吊销令牌API
        //----------------------------------------------------------------------------
        return "https://skin.pixeltail.cn/api/yggdrasil/authserver/invalidate";
    }

    @Override
    public String signout() {
        //----------------------------------------------------------------------------
        //登出API
        //----------------------------------------------------------------------------
        return "https://skin.pixeltail.cn/api/yggdrasil/authserver/signout";
    }

    @Override
    public String profile(UUID uuid) {
        //----------------------------------------------------------------------------
        //查询角色属性API
        //----------------------------------------------------------------------------
        return "https://skin.pixeltail.cn/api/yggdrasil/sessionserver/session/minecraft/profile/" + UUIDUtils.unsign(uuid);
    }

    @Override
    public String profileByUsername(String s) {
        //----------------------------------------------------------------------------
        //按名称批量查询角色API
        //----------------------------------------------------------------------------
        return "https://skin.pixeltail.cn/api/yggdrasil/api/profiles/minecraft" + s;
    }

    @Override
    public String profilesLookup() {
        //----------------------------------------------------------------------------
        //查询角色API
        //----------------------------------------------------------------------------
        return null;
    }

}