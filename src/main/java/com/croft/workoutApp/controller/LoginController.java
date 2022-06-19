package com.croft.workoutApp.controller;

//@Slf4j
//@Controller
//public class LoginController {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @RequestMapping(value = "/process_", method = RequestMethod.POST)
//    public ModelAndView login(HttpSession session, @RequestParam String email, @RequestParam String password) throws Exception {
//        ModelAndView response = new ModelAndView();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String loggedUserEmail = authentication.getName();
//        User user = userRepo.findByEmail(loggedUserEmail).get();
//
//        if(user != null){
//            log.info(user.toString());
//            response.addObject("user", user);
//            session.setAttribute("user_id", user.getId());
//            response.setViewName("redirect:/home");
//        } else {
//            response.setViewName("home");
//        }
//        return response;
//    }
//
//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logout(HttpSession session){
//
//        session.invalidate();
//        return "redirect:/home";
//    }
//}
