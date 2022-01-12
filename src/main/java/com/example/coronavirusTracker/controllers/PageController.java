package com.example.coronavirusTracker.controllers;

import com.example.coronavirusTracker.models.LocationStats;
import com.example.coronavirusTracker.services.CoronaVirusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    CoronaVirusData coronaVirusData;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusData.getAllStats();
        int totalReportedCases = Integer.parseInt(String.valueOf(allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum()));
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);

        return "home";
    }

}
