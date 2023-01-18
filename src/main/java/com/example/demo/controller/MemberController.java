package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<Object> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getMember(@PathVariable("id") Long id) {
        return memberService.getMember(id);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Member member) {
        return memberService.add(member);
    }

    @PutMapping(path = "{id}/update")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Member member) {
        return memberService.update(id, member);
    }

    @DeleteMapping(path = "{id}/delete")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return memberService.delete(id);
    }

}
