package own.stu.redis.oneMaster.autoComplete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.redis.oneMaster.autoComplete.service.AutoCompleteService;

import java.util.*;

@RequestMapping("auto")
@RestController
public class AutoCompleteController {

    @Autowired
    private AutoCompleteService autoCompleteService;

    private static Random random = new Random(new Date().getTime());

    @RequestMapping("join")
    public String addGroup(String groupId, String user) {
        autoCompleteService.joinGroup(groupId, user);
        return "SUCCESS";
    }

    @RequestMapping("join-fake")
    public String addGroupFake(String groupId, String user) {
        user = getRandomStr(6);
        autoCompleteService.joinGroup(groupId, user);
        return "SUCCESS";
    }

    @RequestMapping("leave")
    public String leaveGroup(String groupId, String user) {
        autoCompleteService.leaveGroup(groupId, user);
        return "SUCCESS";
    }

    @RequestMapping("complete")
    public List<String> complete(String groupId, String userPrefix) {
        return autoCompleteService.autoCompleteOnPrefix(groupId, userPrefix);
    }

    private static String allChars = "abcdefghijklmnopqrstuvwxyz";

    public static String getRandomStr(int length) {

        StringBuffer sb = new StringBuffer(length);
        Random random = new Random();
        int i = 0;
        int strLen = allChars.length() - 1;
        while (true) {
            i = random.nextInt(strLen);
            sb.append(allChars.charAt(i));
            if (sb.length() >= length) {
                break;
            }
        }
        return sb.toString();
    }
}
