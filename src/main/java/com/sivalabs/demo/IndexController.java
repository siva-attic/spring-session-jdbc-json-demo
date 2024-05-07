package com.sivalabs.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class IndexController {

    @GetMapping("/")
    String index(Model model, HttpSession session) {
        Counter counter = (Counter)session.getAttribute("Counter");
        long counterVal = 0;
        if(counter != null) {
            counterVal = counter.getValue();
        }
        model.addAttribute("counter", counterVal);
        return "index";
    }

    @PostMapping("/increment")
    String increment(HttpSession session) {
        Counter counter = (Counter)session.getAttribute("Counter");
        if(counter == null) {
            counter = new Counter(0);
        }
        counter.increment();
        session.setAttribute("Counter", counter);
        return "redirect:/";
    }
}

class Counter {
    long value;

    public Counter() {
    }

    public Counter(long value) {
        this.value = value;
    }

    public void increment() {
        this.value = value + 1;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
