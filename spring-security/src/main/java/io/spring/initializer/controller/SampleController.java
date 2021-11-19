package io.spring.initializer.controller;

import io.spring.initializer.config.SecurityLogger;
import io.spring.initializer.custom.CurrentUser;
import io.spring.initializer.domain.Account;
import io.spring.initializer.service.SampleService;
import java.security.Principal;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class SampleController {

  private final SampleService sampleService;

  @GetMapping("/")
  public String index(Model model, @CurrentUser Account account) {
    if (account == null) {
      model.addAttribute("message", "Hello Spring Security");
    } else {
      model.addAttribute("message", "Hello " + account.getUsername());
    }

    return "index";
  }

  @GetMapping("/info")
  public String info(Model model) {
    model.addAttribute("message", "Hello Info");
    return "info";
  }

  @GetMapping("/dashboard")
  public String dashboard(Model model, Principal principal) {
    model.addAttribute("message", "Hello " + principal.getName());
    return "dashboard";
  }

  @GetMapping("/admin")
  public String admin(Model model, Principal principal) {
    model.addAttribute("message", "Hello Admin, " + principal.getName());
    return "admin";
  }

  @GetMapping("/user")
  public String user(Model model, Principal principal) {
    model.addAttribute("message", "Hello User, " + principal.getName());
    return "user";
  }

  @GetMapping("/async-handler")
  @ResponseBody
  public Callable<String> asyncHandler() {
    SecurityLogger.log("MVC");
    return new Callable<String>() {
      @Override
      public String call() throws Exception {
        SecurityLogger.log("Callable");
        return "Async Handler";
      }
    };
  }

  @GetMapping("/async-service")
  @ResponseBody
  public String asyncService() {
    SecurityLogger.log("MVC, before async service");
    sampleService.asyncService();
    SecurityLogger.log("MVC, after async service");

    return "Async Service";
  }
}
