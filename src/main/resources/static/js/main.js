const signUp = {
    init : function () {
        let inputMemberId = document.querySelector("#inputMemberId");
        inputMemberId.focus();

        let resCode = document.querySelector("#resCode");

        if (resCode.value.length > 0) {
            if (resCode.value == 300) {
                alert("이미 사용중인 아이디입니다.");
            } else if (resCode.value == 500) {
                alert("회원가입에 실패하였습니다.");
            }
        }
    },
    validateForm : function () {
        let memberId = document.querySelector('input[name=memberId]');
        let memberPw = document.querySelector('input[name=memberPw]');
        let confirmPw = document.querySelector('input[name=confirmPw]');

        if (memberId.value.length < 2) {
            alert("2글자 이상의 ID를 입력해주세요.");
            memberId.focus();
            return false;
        }

        if (memberPw.value.length < 6) {
            alert("6글자 이상의 비밀번호를 입력해주세요.");
            memberPw.focus();
            return false;
        }

        if (memberPw.value != confirmPw.value) {
            alert("입력하신 비밀번호가 일치하지 않습니다.");
            confirmPw.focus();
            return false;
        }

        return true;
    }
}

const login = {
    init : function () {
        let inputMemberId = document.querySelector("#inputMemberId");
        inputMemberId.focus();

        let signUpBtn = document.querySelector(".sign-up-btn");
        let resCode = document.querySelector("#resCode");

        function goToSignUpPage() {
            self.location = "/signUp";
        }

        signUpBtn.addEventListener('click', goToSignUpPage);

        if (resCode.value.length > 0) {
            if (resCode.value == 404) {
                alert("회원가입 하지 않은 아이디 입니다.");
            } else if (resCode.value == 200) {
                alert("회원가입 되었습니다.");
            } else if (resCode.value == 405) {
                alert("비밀번호가 일치하지 않습니다.");
            } else if (resCode.value == 600) {
                alert("로그인 후 이용 가능 합니다.");
            }
        }
    },
    validateForm : function () {
        let memberId = document.querySelector('input[name=memberId]');
        let memberPw = document.querySelector('input[name=memberPw]');

        if (memberId.value.length < 2) {
            alert("2글자 이상의 ID를 입력해주세요.");
            memberId.focus();
            return false;
        }

        if (memberPw.value.length < 6) {
            alert("6글자 이상의 비밀번호를 입력해주세요.");
            memberPw.focus();
            return false;
        }

        return true;
    }
}

const list = {
    init : function () {
        let writeBtn = document.querySelector(".write-btn");

        function goToWritePage() {
            self.location = "/boards/write";
        }

        writeBtn.addEventListener('click', goToWritePage);
    }
}

const write = {
    init : function () {
        let postBtn = document.querySelector("#postBtn");
        postBtn.addEventListener("click", write.writeBoard);
    },
    writeBoard : function () {
        editor.save()
            .then((savedData) => {
                write.postBoard(JSON.stringify(savedData));
            })
            .catch((error) => {
                console.log(error);
            });
    },
    postBoard : function (board) {
        console.log("call postBoard");
        console.log(board);
    }
}




