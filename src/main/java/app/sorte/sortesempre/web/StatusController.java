package app.sorte.sortesempre.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class StatusController {
  @GetMapping("/status")
  public Map<String, String> get() {
    return Map.of("app", "sorte-sempre-api", "status", "ok");
  }
}
