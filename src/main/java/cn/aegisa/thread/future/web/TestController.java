package cn.aegisa.thread.future.web;

import cn.aegisa.thread.future.aspect.Time;
import cn.aegisa.thread.future.vo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Using IntelliJ IDEA.
 *
 * @author XIANYINGDA at 2019/7/17 22:01
 */
@SuppressWarnings({"unused", "Duplicates"})
@Controller
@Slf4j
public class TestController {

    @Autowired
    private TaskExecutor taskExecutor;

    private Object ooo;

    @RequestMapping("/test")
    @ResponseBody
    @Time
    public Object doTest() throws Exception {
        log.info("test request");
        int age = 18;
        CompletableFuture<List<Student>> stu1 = CompletableFuture.supplyAsync(() -> {
            List<Student> students = new ArrayList<>();
            students.add(new Student("lxx", age));
            try {
                Thread.sleep(555);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return students;
        }, taskExecutor).exceptionally(ex -> {
            log.error("执行lxx的时候出现了异常", ex);
            return new ArrayList<>();
        }).thenApplyAsync(o -> {
            System.out.println("then" + o);
            this.ooo = o;
            return o;
        }, taskExecutor);

        CompletableFuture<List<Student>> stu2 = CompletableFuture.supplyAsync(() -> {
            List<Student> students = new ArrayList<>();
            students.add(new Student("lxy", age));
            try {
                Thread.sleep(666);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return students;
        }, taskExecutor).exceptionally(ex -> {
            System.out.println("exp:" + ex);
            return new ArrayList<>();
        });
        List<Student> students1 = stu1.get();
        List<Student> students2 = stu2.get();
        System.out.println(students1.toString() + students2.toString());
        return "ok";
    }
}
