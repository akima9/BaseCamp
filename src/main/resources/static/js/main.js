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
        let writeBtn = document.querySelector("#writeBtn");
        let modifyBtn = document.querySelector("#modifyBtn");
        let deleteBtn = document.querySelector("#deleteBtn");
        let listBtn = document.querySelector("#listBtn");

        postBtn.addEventListener("click", write.writeBoard);
        writeBtn.addEventListener("click", write.toggleReadOnly);
        modifyBtn.addEventListener("click", write.modifyBoard);
        listBtn.addEventListener("click", write.goToList);
        deleteBtn.addEventListener("click", write.deleteBoard);
    },
    deleteBoard : function () {
        if (confirm("삭제하시겠습니까?")) {
            let formDeleteBoard = document.querySelector("#formDeleteBoard");
            formDeleteBoard.submit();
        }
    },
    goToList : function () {
        self.location = "/boards/list";
    },
    writeBoard : function () {
        editor.save()
            .then((savedData) => {
                write.postBoard(savedData);
            })
            .catch((error) => {
                console.log(error);
            });
    },
    modifyBoard : function () {
        editor.save()
            .then((savedData) => {
                write.putBoard(savedData);
            })
            .catch((error) => {
                console.log(error);
            });
    },
    putBoard : function (board) {
        let boardId = document.querySelector("input[name=boardId]");
        board.boardId = boardId.value;
        write.putData("/rest/boards", board).then((data) => {
            alert("수정 되었습니다.");
            write.toggleReadOnly(); //editor readOnly로 변경
            write.changeReadMode();
            // write.toggleReadOnly(); //editor readOnly로 변경
            // let modifyBtn = document.querySelector("#modifyBtn");
            // writeBtn.classList.remove("d-none"); // 수정하기 버튼 노출
            // modifyBtn.classList.add("d-none"); // 변경 버튼 숨김
        });
    },
    postBoard : function (board) {
        write.postData("/rest/boards", board).then((data) => {
            alert("등록 되었습니다.");
            
            let boardId = document.querySelector("input[name=boardId]");
            boardId.value = data.boardId;
            write.toggleReadOnly(); //editor readOnly로 변경
            
            // let postBtn = document.querySelector("#postBtn");
            // let writeBtn = document.querySelector("#writeBtn");
            // let modifyBtn = document.querySelector("#modifyBtn");
            // let deleteBtn = document.querySelector("#deleteBtn");

            postBtn.classList.add("d-none"); // 등록 버튼 숨김
            writeBtn.classList.remove("d-none"); // 수정하기 버튼 노출
            deleteBtn.classList.remove("d-none"); // 삭제 버튼 노출
            // writeBtn.addEventListener("click", write.toggleReadOnly);
            // modifyBtn.addEventListener("click", write.modifyBoard);
        });
    },
    putData : async function (url = "", data = {}) {
        // 옵션 기본 값은 *로 강조
        const response = await fetch(url, {
            method: "PUT", // *GET, POST, PUT, DELETE 등
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json",
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            body: JSON.stringify(data), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
        });
        return response.json(); // JSON 응답을 네이티브 JavaScript 객체로 파싱
    },
    postData : async function (url = "", data = {}) {
        // 옵션 기본 값은 *로 강조
        const response = await fetch(url, {
            method: "POST", // *GET, POST, PUT, DELETE 등
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json",
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            body: JSON.stringify(data), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
        });
        return response.json(); // JSON 응답을 네이티브 JavaScript 객체로 파싱
    },
    toggleReadOnly : async function () {
        let readOnlyState = await editor.readOnly.toggle();
        if (readOnlyState == false) {
            write.changeModifyMode();
            // let writeBtn = document.querySelector("#writeBtn");
            // let modifyBtn = document.querySelector("#modifyBtn");

            // writeBtn.classList.add("d-none");
            // modifyBtn.classList.remove("d-none");
        }
    },
    changeModifyMode : function () {
        writeBtn.classList.add("d-none");
        modifyBtn.classList.remove("d-none");
    },
    changeReadMode : function () {
        writeBtn.classList.remove("d-none");
        modifyBtn.classList.add("d-none");
    }
}

const VIEW = {
    init : function () {
        let writeBtn = document.querySelector("#writeBtn");     // 수정하기 버튼
        let modifyBtn = document.querySelector("#modifyBtn");   // 변경 버튼
        let listBtn = document.querySelector("#listBtn");       // 목록 버튼
        let deleteBtn = document.querySelector("#deleteBtn");   // 삭제 버튼
        
        if (writeBtn !== null) {
            writeBtn.addEventListener("click", VIEW.toggleReadOnly);
        }

        if (modifyBtn !== null) {
            modifyBtn.addEventListener("click", VIEW.modifyBoard);
        }

        listBtn.addEventListener("click", VIEW.goToList);

        if (deleteBtn !== null) {
            deleteBtn.addEventListener("click", VIEW.deleteBoard);
        }
    },
    deleteBoard : function () {
        if (confirm("삭제하시겠습니까?")) {
            let formDeleteBoard = document.querySelector("#formDeleteBoard");
            formDeleteBoard.submit();
        }
    },
    modifyBoard : function () {
        editor.save()
            .then((savedData) => {
                VIEW.putBoard(savedData);
            })
            .catch((error) => {
                console.log(error);
            });
    },
    putBoard : function (board) {
        let boardId = document.querySelector("input[name=boardId]");
        board.boardId = boardId.value;
        VIEW.putData("/rest/boards", board).then((data) => {
            alert("수정 되었습니다.");
            VIEW.toggleReadOnly(); //editor readOnly로 변경
            VIEW.changeReadMode();
        });
    },
    putData : async function (url = "", data = {}) {
        // 옵션 기본 값은 *로 강조
        const response = await fetch(url, {
            method: "PUT", // *GET, POST, PUT, DELETE 등
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json",
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            body: JSON.stringify(data), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
        });
        return response.json(); // JSON 응답을 네이티브 JavaScript 객체로 파싱
    },
    goToList : function () {
        self.location = "/boards/list";
    },
    toggleReadOnly : async function () {
        let readOnlyState = await editor.readOnly.toggle();
        if (readOnlyState == false) {
            VIEW.changeModifyMode();
        }
    },
    changeModifyMode : function () {
        writeBtn.classList.add("d-none");
        modifyBtn.classList.remove("d-none");
    },
    changeReadMode : function () {
        writeBtn.classList.remove("d-none");
        modifyBtn.classList.add("d-none");
    }
}