// JavaScript
// Elements
const el = {
  signUpHome: document.getElementById('sign-up'),
  signInHome: document.getElementById('sign-in'),
  btnHome: document.querySelector('.btn-back'),
  pageMain: document.querySelector('.main'),
  pageHome: document.querySelector('.home'),
  pageSignUp: document.querySelector('.sign-up'),
  formArea: document.querySelector('.form-area'),
  sideSignLeft: document.querySelector('.signup-left'),
  sideSignRight: document.querySelector('.signup-right'),
  formSignUp: document.querySelector('.form-area-signup'),
  formSignIn: document.querySelector('.form-area-signin'),
  linkUp: document.querySelector('.link-up'),
  linkIn: document.querySelector('.link-in'),
  labels: document.getElementsByTagName('label'),
  inputs: document.getElementsByTagName('input'),
  signUpForm: document.getElementById('signup-form'),
  signInForm: document.getElementById('signin-form'),
  emailError: document.getElementById('email-error'),
  emailInError: document.getElementById('email-in-error'),
};

// ADD Events
// Show the page Sign Up
el.signUpHome.addEventListener('click', function(e) {
  showSign(e, 'signup');
});
el.linkUp.addEventListener('click', function(e) {
  showSign(e, 'signup');
});

// Show the page sign in
el.signInHome.addEventListener('click', function(e) {
  showSign(e, 'signin');
});
el.linkIn.addEventListener('click', function(e) {
  showSign(e, 'signin');
});

// Show the page Home
el.btnHome.addEventListener('click', showHome);

// Sign Up form validation
el.signUpForm.addEventListener('submit', function(e) {
  if (!validateEmail(el.signUpForm.email.value)) {
    e.preventDefault();
    el.emailError.textContent = '이메일에 @를 포함해야 합니다.';
  } else {
    el.emailError.textContent = '';
  }
});

// Sign In form validation
el.signInForm.addEventListener('submit', function(e) {
  if (!validateEmail(el.signInForm.email.value)) {
    e.preventDefault();
    el.emailInError.textContent = '이메일에 @를 포함해야 합니다.';
  } else {
    el.emailInError.textContent = '';
  }
});

// Functions Events
// function to show screen Home
function showHome(event) {
  setTimeout(function() {
    el.sideSignLeft.style.padding = '0';
    el.sideSignLeft.style.opacity = '0';
    el.sideSignRight.style.opacity = '0';
    el.sideSignRight.style.backgroundPositionX = '235%';
    el.formArea.style.opacity = '0';
    setTimeout(function() {
      el.pageSignUp.style.opacity = '0';
      el.pageSignUp.style.display = 'none';
      for (input of el.inputs) {
        input.value = '';
      }
    }, 900);
  }, 100);
  setTimeout(function() {
    el.pageHome.style.display = 'flex';
  }, 1100);
  setTimeout(function() {
    el.pageHome.style.opacity = '1';
  }, 1200);
}

// function to show screen Sign up/Sign in
function showSign(event, sign) {
  if (sign === 'signup') {
    el.formSignUp.style.display = 'flex';
    el.formSignIn.style.opacity = '0';
    setTimeout(function() {
      el.formSignUp.style.opacity = '1';
    }, 100);
    el.formSignIn.style.display = 'none';
  } else {
    el.formSignIn.style.display = 'flex';
    el.formSignUp.style.opacity = '0';
    setTimeout(function() {
      el.formSignIn.style.opacity = '1';
    }, 100);
    el.formSignUp.style.display = 'none';
  }
  el.pageHome.style.opacity = '0';
  setTimeout(function() {
    el.pageHome.style.display = 'none';
  }, 700);
  setTimeout(function() {
    el.pageSignUp.style.display = 'flex';
    el.pageSignUp.style.opacity = '1';
    setTimeout(function() {
      el.sideSignLeft.style.padding = '20px';
      el.sideSignLeft.style.opacity = '1';
      el.sideSignRight.style.opacity = '1';
      el.sideSignRight.style.backgroundPositionX = '230%';
      el.formArea.style.opacity = '1';
    }, 10);
  }, 900);
}

// Function to validate email
function validateEmail(email) {
  return email.includes('@');
}

// Behavior of the inputs and labels
for (input of el.inputs) {
  input.addEventListener('keydown', function() {
    this.labels[0].style.top = '10px';
  });
  input.addEventListener('blur', function() {
    if (this.value === '') {
      this.labels[0].style.top = '25px';
    }
  });
}

// jQuery 코드
$(document).ready(function() {
  $('#signup-form').on('submit', function(e) {
    e.preventDefault(); // 기본 폼 제출 동작을 막음
    console.log("회원가입버튼동작");

    // 유효성 검사
    var isValid = true;
    var name = $('#name').val().trim();
    var email = $('#email').val().trim();
    var userId = $('#id').val().trim();
    var password = $('#password').val().trim();

    if (name === "") {
      isValid = false;
      alert("이름을 입력해주세요.");
    }

    if (email === "") {
      isValid = false;
      $('#email-error').text("이메일을 입력해주세요.");
    } else if (!validateEmail(email)) {
      isValid = false;
      $('#email-error').text("유효한 이메일 형식이 아닙니다.");
    } else {
      $('#email-error').text("");
    }

    if (userId === "") {
      isValid = false;
      alert("아이디를 입력해주세요.");
    }

    if (password === "") {
      isValid = false;
      alert("비밀번호를 입력해주세요.");
    }

    // 유효성 검사 통과 시 백엔드로 데이터 전송
    if (isValid) {

      $.ajax({
        type: 'POST',
        url: '/api/users/signup', // 백엔드 엔드포인트 URL
        contentType : "application/json",
        data: JSON.stringify({
            "userId": userId,
            "email": email,
            "username":name,
            "password":password
        }),
        success: function(response) {
          alert('이메일 인증을 하면 회원가입이 완료됩니다.');
        },
        error: function(error) {
          var response = error.responseJSON;
          if (response && response.code === 'GLOBAL_400_2') {
            alert(response.message);
          } else {
            alert('회원가입 중 오류가 발생했습니다. 다시 시도해주세요.');
          }
        }
      });
    }
  });

  // 이메일 유효성 검사 함수
  function validateEmail(email) {
    var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  }
});
