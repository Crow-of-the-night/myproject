package org.sheehan.fortune.web;


import com.alibaba.fastjson.JSON;
import org.sheehan.fortune.entity.Person;
import org.sheehan.fortune.service.PictureCurator;
import org.sheehan.fortune.util.myarray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Random;


@Controller
public class turntablecontroller {

    @Value("${chose.person}")
    private String chose;

    @Autowired
    private PictureCurator pictureCurator;

    @RequestMapping(value = "/luck_dog")
    @ResponseBody
    public Person luckDog(String participants){
        String[] strings = new String[0];
        if(participants.indexOf(",")>0){
            strings = participants.split(",");
        } else if(participants.indexOf(" ")>0){
            strings = participants.split(" ");
        } else if(participants.indexOf("，")>0){
            strings = participants.split("，");
        } else if(participants.indexOf("、")>0){
            strings = participants.split("、");
        } else if(participants.indexOf("\n")>0){
            strings = participants.split("\n");
        } else {
            strings[0] = participants;
        }
        Random random = new Random();
        int num = random.nextInt(strings.length);
        Person person = new Person(num, strings[num],strings.length);
        return person;
    }

    @RequestMapping(value = "/chosen_one")
    @ResponseBody
    public Person chosenOne(String participants){
        int the_chose = Integer.parseInt(chose);
        String[] strings = new String[0];
        if(participants.indexOf(",")>0){
            strings = participants.split(",");
        } else if(participants.indexOf(" ")>0){
            strings = participants.split(" ");
        } else if(participants.indexOf("，")>0){
            strings = participants.split("，");
        } else if(participants.indexOf("、")>0){
            strings = participants.split("、");
        } else if(participants.indexOf("\n")>0){
            strings = participants.split("\n");
        } else {
            strings[0] = participants;
        }
        Person person = null;
        if(strings.length >= the_chose){
            person = new Person(the_chose-1, strings[the_chose-1],strings.length);
        }else {
            person = new Person(0,strings[0],strings.length);
        }
        return person;
    }

    @RequestMapping(value = "/fortune")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("fortune");
        return mv;
    }

    @RequestMapping(value = "/turntable")
    public @ResponseBody
    String turntable(String participants) {
        String[] strings = new String[0];
        if(participants.indexOf(",")>0){
            strings = participants.split(",");
        } else if(participants.indexOf(" ")>0){
            strings = participants.split(" ");
        } else if(participants.indexOf("，")>0){
            strings = participants.split("，");
        } else if(participants.indexOf("、")>0){
            strings = participants.split("、");
        } else if(participants.indexOf("\n")>0){
            strings = participants.split("\n");
        } else {
            strings[0] = participants;
        }
        strings = myarray.reverse(strings);
        try {
            pictureCurator.CuratePicture(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("result.png");
        FileInputStream inputStream = null;
        String json = "";
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            json = JSON.toJSONString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    @RequestMapping(value = "/Rectangle")
    public @ResponseBody
    String Rectangle(String participants) {
        String[] strings = new String[0];
        if(participants.indexOf(",")>0){
            strings = participants.split(",");
        } else if(participants.indexOf(" ")>0){
            strings = participants.split(" ");
        } else if(participants.indexOf("，")>0){
            strings = participants.split("，");
        } else if(participants.indexOf("、")>0){
            strings = participants.split("、");
        } else if(participants.indexOf("\n")>0){
            strings = participants.split("\n");
        } else {
            strings[0] = participants;
        }
        String[] strings_five_hundred = null;
        if(strings.length<250){
            strings_five_hundred = new String[500];
            int temp = 0;
            for(int i = 0;i<strings_five_hundred.length;i++){
                if(temp<strings.length){
                    strings_five_hundred[i] = strings[temp];
                    temp++;
                } else {
                    temp = 0;
                    strings_five_hundred[i] = strings[temp];
                    temp++;
                }
            }
        } else {
            strings_five_hundred = new String[strings.length*2];
            int temp = 0;
            for(int i = 0;i<strings_five_hundred.length;i++){
                if(temp<strings.length){
                    strings_five_hundred[i] = strings[temp];
                    temp++;
                } else {
                    temp = 0;
                    strings_five_hundred[i] = strings[temp];
                    temp++;
                }
            }
        }
        pictureCurator.CurateRectanglePicture(strings_five_hundred);
        File file = new File("result.png");
        FileInputStream inputStream = null;
        String json = "";
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            json = JSON.toJSONString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }


    @RequestMapping(value = "/image",produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] myimage(HttpServletResponse response) throws IOException {
        File file = new File("result.png");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
}
