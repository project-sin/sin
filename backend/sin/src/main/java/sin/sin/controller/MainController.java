package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.dto.EventResponse;
import sin.sin.dto.ProductMain.MainResponse;
import sin.sin.service.MainService;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/main/index")
public class MainController {

    private final MainService mainService;

    @GetMapping("/main-banner")
    public ResponseEntity<List<EventResponse>> mainBanner() {
        return ResponseEntity.ok().body(mainService.findMainBanner());
    }

    @GetMapping("/today-recommendation")
    public ResponseEntity<List<MainResponse>> recommedProduct() {
        return ResponseEntity.ok().body(mainService.findRecommendProduct());
    }

    @GetMapping("/cheap-product")
    public ResponseEntity<List<MainResponse>> cheapProduct() {
        return ResponseEntity.ok().body(mainService.findCheapProduct());
    }

    @GetMapping("/md-choice")
    public ResponseEntity<HashMap<String, List<MainResponse>>> mdChoice() {
        return ResponseEntity.ok().body(mainService.findMdChoice());
    }
}
