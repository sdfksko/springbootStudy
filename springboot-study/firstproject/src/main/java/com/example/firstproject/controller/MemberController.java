//package com.example.firstproject.controller;
//
//import com.example.firstproject.dto.MemberForm;
//import com.example.firstproject.entity.Member;
//import com.example.firstproject.repository.MemberRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//
//@Slf4j
//@Controller
//public class MemberController {
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @GetMapping("/signup")
//    public String signUpPage() {
//        return "members/newMember";
//    }
//
//    @PostMapping("/join")
//    public String memberShip(MemberForm form) {
//        log.info(form.toString());
//
//        Member member = form.toEntity();
//        log.info(member.toString());
//
//        Member saved = memberRepository.save(member);
//        log.info(saved.toString());
//        return "redirect:/member/" + saved.getId();
//    }
//
//    @GetMapping("/member/{id}")
//    public String show(@PathVariable Long id, Model model) {
//        Member memberEntity = memberRepository.findById(id).orElse(null);
//        model.addAttribute("member", memberEntity);
//        return "members/show";
//    }
//
//    @GetMapping("/members")
//    public String index(Model model) {
//        List<Member> memberList = (List<Member>) memberRepository.findAll();
//        model.addAttribute("memberList", memberList);
//        return "members/index";
//    }
//
//    @GetMapping("/member/{id}/edit")
//    public String edit(@PathVariable Long id, Model model) {
//        Member memberEntity = memberRepository.findById(id).orElse(null);
//        model.addAttribute("memberEntity", memberEntity);
//        return "members/edit";
//    }
//
//    @PostMapping("/members/update")
//    public String update(MemberForm form) {
//        Member memberEntity = form.toEntity();
//        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
//        if(target != null) {
//            memberRepository.save(memberEntity);
//        }
//        return "redirect:/member/" + memberEntity.getId();
//    }
//
//    @GetMapping("/member/{id}/delete")
//    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
//        Member memberEntity = memberRepository.findById(id).orElse(null);
//        if(memberEntity != null) {
//            rttr.addFlashAttribute("msg", "삭제되었습니다");
//            memberRepository.delete(memberEntity);
//        }
//        return "redirect:/members";
//    }
//}
