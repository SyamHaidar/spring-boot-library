package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public ResponseEntity<Object> getAllMembers() {
        try {
            return new ResponseEntity<>(memberRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getMember(Long id) {
        try {
            Optional<Member> data = memberRepository.findById(id);
            if (data.isPresent()) {
                Member _member = data.get();
                return new ResponseEntity<>(_member, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> add(Member member) {
        try {
            Member _member = memberRepository.save(new Member(
                    member.getFullName(),
                    member.getAddress(),
                    member.getGender(),
                    member.getDob(),
                    member.getMobile()
            ));
            return new ResponseEntity<>(_member, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> update(Long id, Member member) {
        try {
            Optional<Member> data = memberRepository.findById(id);
            if (data.isPresent()) {
                Member _member = data.get();
                _member.setFullName(member.getFullName());
                _member.setAddress(member.getAddress());
                _member.setGender(member.getGender());
                _member.setDob(member.getDob());
                _member.setMobile(member.getMobile());
                return new ResponseEntity<>(memberRepository.save(_member), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        try {
            boolean _member = memberRepository.existsById(id);
            if (_member) {
                memberRepository.deleteById(id);
                return new ResponseEntity<>("success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
