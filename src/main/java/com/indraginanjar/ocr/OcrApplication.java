package com.indraginanjar.ocr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class OcrApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcrApplication.class, args);
	}

	@GetMapping("/")
	public String index(Model model) {
		System.loadLibrary("opencv_java470");
		TextImage image = new TextImage();
		List<Map<String, String>> imageList = new ArrayList<>();

		Map<String, String> image1Map = new HashMap<>();
		image1Map.put("fileName", "images/ImageWithWords1.jpg");
		image1Map.put("language", "eng");


		String image1Text = image.read(
			"src/main/resources/static/images/ImageWithWords1.jpg", "300", 612, 406, "eng"
		);

		image1Map.put("text", image1Text);
        imageList.add(image1Map);

		Map<String, String> image2Map = new HashMap<>();
		image2Map.put("fileName", "images/ImageWithWords2.png");
		image2Map.put("language", "eng");

		String image2Text = image.read(
			"src/main/resources/static/images/ImageWithWords2.png", "96", 800, 585, "eng"
		);

		image2Map.put("text", image2Text);
        imageList.add(image2Map);

		Map<String, String> image3Map = new HashMap<>();
		image3Map.put("fileName", "images/ImageWithWords3.jpg");
		image3Map.put("language", "eng");

		String image3Text = image.read(
			"src/main/resources/static/images/ImageWithWords3.jpg","72", 1046, 1046, "eng"
		);
		
		image3Map.put("text", image3Text);
        imageList.add(image3Map);

		Map<String, String> image4Map = new HashMap<>();
		image4Map.put("fileName", "images/ImageWithWords4.jpg");
		image4Map.put("language", "chi_sim");

		String image4Text = image.read(
			"src/main/resources/static/images/ImageWithWords4.jpg", "96", 800, 687, "chi_sim"
		);
		
		image4Map.put("text", image4Text);
        imageList.add(image4Map);
	
		model.addAttribute("images", imageList.toArray());

		return "index";
	}
}
