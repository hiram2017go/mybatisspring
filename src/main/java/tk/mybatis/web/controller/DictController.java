package tk.mybatis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.web.model.SysDict;
import tk.mybatis.web.service.DictService;

import java.util.List;

/**
 * Created by zhaiyongyi on 2018/5/26.
 */

@Controller
@RequestMapping("/dicts")
public class DictController {

    @Autowired
    private DictService dictService;

    //显示字典数据列表
    @RequestMapping
    public ModelAndView dicts(SysDict sysDict, Integer offset, Integer limit){
        ModelAndView mv = new ModelAndView("dicts");
        List<SysDict> dicts = dictService.findBySysDict(sysDict, offset, limit);
        mv.addObject("dicts", dicts);
        return mv;
    }

    //新增或修改字典信息页面，使用get跳转页面
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(Long id){
        ModelAndView mv = new ModelAndView("dict_add");
        SysDict sysDict;
        if(id == null){
            //如果id不存在，就是新增数据
            sysDict = new SysDict();
        }else{
            //存在则是更新
            sysDict = dictService.findById(id);
        }

        mv.addObject("model", sysDict);
        return mv;
    }

    //新增或修改字段信息，通过表单post提交数据
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView save(SysDict sysDict){
        ModelAndView mv = new ModelAndView();
        try{
            dictService.saveOrUpdate(sysDict);
            mv.setViewName("redirect:/dicts");
        }catch (Exception ex){
            mv.setViewName("dict_add");
            mv.addObject("msg", ex.getMessage());
            mv.addObject("model", sysDict);
        }

        return mv;
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap delete(@RequestParam Long id){
        ModelMap modelMap = new ModelMap();
        try {
            boolean success = dictService.deleteById(id);
            modelMap.put("success", success);
        }catch (Exception ex){
            modelMap.put("success", false);
            modelMap.put("msg", ex.getMessage());
        }

        return modelMap;
    }

}
