package net.zhuoyue.dao;

import java.util.List;
import java.util.Map;

import net.zhuoyue.vo.ZoyareSetting;

public interface IZoyareDao {

    public boolean addSetting(ZoyareSetting zoy);
    public void addSetting(List<ZoyareSetting> zList);
    
    public Map<String, String> findAllSettingsMap(String pageid);
    public List<ZoyareSetting> findAllSettings(String pageid);
}
