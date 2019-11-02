package own.stu.redis.oneMaster.fakeDistribute.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("distribute")
@Controller
public class FakeDistributeController {

    @RequestMapping("split")
    public String splitFile() {


        return "Success";
    }
}
