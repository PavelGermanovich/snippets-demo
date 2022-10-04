package com.germanovich.codesnippets.controller;

import com.germanovich.codesnippets.dto.CodeDto;
import com.germanovich.codesnippets.dto.CreateCodeResponse;
import com.germanovich.codesnippets.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private CodeService codeService;

    @PostMapping("/api/code/new")
    @ResponseBody
    public CreateCodeResponse createNewCode(@RequestBody CodeDto codeDto) {
        return new CreateCodeResponse(codeService.saveNewCode(codeDto).toString());
    }

    @GetMapping(value = "/code/new")
    public String getNewCodeForm() {
        return "createNewForm";
    }

    @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CodeDto getApiCode(@PathVariable("id") UUID id) {
        return codeService.getCodeById(id);
    }

    @GetMapping(value = "/code/{id}")
    public String getApiCode(@PathVariable("id") UUID id, Model model) {
        CodeDto code = codeService.getCodeById(id);
        model.addAttribute("codeDto", code);
        return "snippet";
    }

    @GetMapping("api/code/latest")
    @ResponseBody
    public List<CodeDto> getLatestCodeSnippets() {
        return codeService.getLatestCodeSnippets();
    }

    @GetMapping(value = "/code/latest")
    public String getLatestCodeSnippetsHtml(Model model) {
        model.addAttribute("snippets", codeService.getLatestCodeSnippets());
        return "latestCodeSnippets";
    }
}