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
    goToList : function (event) {
        let listBtn = event.target;
        let page = listBtn.getAttribute("data-page");
        self.location = "/boards/list?page=" + page;
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

const COMMENT = {
    init : function () {
        this.getComments();
    },
    validateForm : function () {
        let form = document.querySelector("#commentForm");
        let inputWriter = form.querySelector("input[name=writer]");
        let inputContent = form.querySelector("input[name=content]");

        if (inputWriter.value.length === 0) {
            if (confirm("로그인이 필요합니다. 로그인 하시겠습니까?")) {
                self.location = "/login";
                return false;
            }
            return false;
        }

        if (inputContent.value.length === 0) {
            alert("댓글을 입력해주세요.");
            return false;
        }
        
        return true;
    },
    timestampToDate : function (data) {
        let date = new Date(data);

        let year = date.getFullYear().toString().slice(-2); // 년도에서 뒤의 두 자리만 추출
        let month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 2자리로 패딩
        let day = String(date.getDate()).padStart(2, '0'); // 날짜를 2자리로 패딩

        let formattedDate = `${year}/${month}/${day}`;
        return formattedDate;
    },
    createCommentDom : function (element) {
        let loginId = document.querySelector("input[name=loginId]").value;
        
        let commentWrap = document.querySelector(".comment_wrap");
        
        let commentDiv = document.createElement("div");
        commentDiv.setAttribute("class", "comment");
        commentDiv.setAttribute("data-commentId", element.commentId);
        commentDiv.setAttribute("data-writer", element.writer);
        
        let rowDiv = document.createElement("div");
        rowDiv.setAttribute("class", "row");
        
        let createdAtDiv = document.createElement("div");
        createdAtDiv.setAttribute("class", "col-auto me-auto fw-light fst-italic text-secondary");
        createdAtDiv.innerHTML = this.timestampToDate(element.createdAt);

        let buttonDiv = document.createElement("div");
        buttonDiv.setAttribute("class", "col-auto");

        let editBtn = document.createElement("button");
        editBtn.setAttribute("type", "button");
        editBtn.setAttribute("class", "btn btn-light btn-sm editCommentBtn");
        editBtn.innerHTML = "댓글 편집";

        let modifyBtn = document.createElement("button");
        modifyBtn.setAttribute("type", "button");
        modifyBtn.setAttribute("class", "btn btn-primary btn-sm d-none modifyCommentBtn");
        modifyBtn.innerHTML = "댓글 수정";

        let deleteBtn = document.createElement("button");
        deleteBtn.setAttribute("type", "button");
        deleteBtn.setAttribute("class", "btn btn-outline-danger btn-sm ms-1 deleteCommentBtn");
        deleteBtn.innerHTML = "댓글 삭제";

        let contents = document.createElement("p");
        contents.setAttribute("class", "contents");
        contents.innerHTML = element.content;

        buttonDiv.appendChild(editBtn);
        buttonDiv.appendChild(modifyBtn);
        buttonDiv.appendChild(deleteBtn);
        rowDiv.appendChild(createdAtDiv);
        if (loginId == element.writer) rowDiv.appendChild(buttonDiv);
        commentDiv.appendChild(rowDiv);
        commentDiv.appendChild(contents);
        commentWrap.appendChild(commentDiv);

        editBtn.addEventListener("click", this.editComment);
        modifyBtn.addEventListener("click", this.modifyComment);
        deleteBtn.addEventListener("click", this.deleteComment);
        // <div>
        //     <div class="row">
        //         <div class="col-auto me-auto fw-light fst-italic text-secondary">createdAt</div>
        //         <div class="col-auto">
        //         <button type="button" id="modifyCommentBtn" class="btn btn-light btn-sm">수정</button>
        //         <button type="button" id="deleteCommentBtn" class="btn btn-outline-danger btn-sm">삭제</button>
        //         </div>
        //     </div>
        //     <p>내용</p>
        // </div>
    },
    deleteComment : function (event) {
        if (confirm("댓글을 삭제하시겠습니까?")) {
            let deleteBtn = event.target;
            let parentDiv = deleteBtn.closest(".comment");
            let commentId = parentDiv.getAttribute("data-commentid");
            let boardId = document.querySelector("input[name=boardId]").value;
            
            let form = document.createElement("form");
            form.setAttribute("method", "POST");
            form.setAttribute("action", "/comments");

            let methodInput = document.createElement("input");
            methodInput.setAttribute("type", "hidden");
            methodInput.setAttribute("name", "_method");
            methodInput.setAttribute("value", "DELETE");
            form.appendChild(methodInput);
            
            let commentIdInput = document.createElement("input");
            commentIdInput.setAttribute("type", "hidden");
            commentIdInput.setAttribute("name", "commentId");
            commentIdInput.setAttribute("value", commentId);
            form.appendChild(commentIdInput);

            let boardIdInput = document.createElement("input");
            boardIdInput.setAttribute("type", "hidden");
            boardIdInput.setAttribute("name", "boardId");
            boardIdInput.setAttribute("value", boardId);
            form.appendChild(boardIdInput);

            document.body.appendChild(form);
            form.submit();
        }
    },
    editComment : function (event) {
        let editBtn = event.target;
        let parentDiv = editBtn.closest(".comment");
        let contentsDiv = parentDiv.querySelector(".contents");

        let contentInput = document.createElement("input");
        contentInput.setAttribute("type", "text");
        contentInput.setAttribute("class", "form-control mt-1");
        contentInput.setAttribute("name", "content");

        let modifyBtn = parentDiv.querySelector(".modifyCommentBtn");
        modifyBtn.classList.remove("d-none");
        editBtn.classList.add("d-none");

        contentInput.value = contentsDiv.textContent;
        parentDiv.appendChild(contentInput);
        contentsDiv.classList.add("d-none");
    },
    modifyComment : function (event) {
        let boardId = document.querySelector("input[name=boardId]").value;
        let modifyBtn = event.target;
        let parentDiv = modifyBtn.closest(".comment");
        let commentId = parentDiv.getAttribute("data-commentid");
        let writer = parentDiv.getAttribute("data-writer");
        let content = parentDiv.querySelector("input[name=content]").value;

        let form = document.createElement("form");
        form.setAttribute("method", "POST");
        form.setAttribute("action", "/comments");

        let methodInput = document.createElement("input");
        methodInput.setAttribute("type", "hidden");
        methodInput.setAttribute("name", "_method");
        methodInput.setAttribute("value", "PUT");
        form.appendChild(methodInput);
        
        let commentIdInput = document.createElement("input");
        commentIdInput.setAttribute("type", "hidden");
        commentIdInput.setAttribute("name", "commentId");
        commentIdInput.setAttribute("value", commentId);
        form.appendChild(commentIdInput);

        let writerInput = document.createElement("input");
        writerInput.setAttribute("type", "hidden");
        writerInput.setAttribute("name", "writer");
        writerInput.setAttribute("value", writer);
        form.appendChild(writerInput);

        let contentInput = document.createElement("input");
        contentInput.setAttribute("type", "hidden");
        contentInput.setAttribute("name", "content");
        contentInput.setAttribute("value", content);
        form.appendChild(contentInput);

        let boardIdInput = document.createElement("input");
        boardIdInput.setAttribute("type", "hidden");
        boardIdInput.setAttribute("name", "boardId");
        boardIdInput.setAttribute("value", boardId);
        form.appendChild(boardIdInput);

        document.body.appendChild(form);
        form.submit();
    },
    getComments : function () {
        let boardId = document.querySelector("input[name=boardId]").value;
        let params = {
            "boardId": boardId
        };
        let queryString = new URLSearchParams(params).toString();
        this.getData("/comments?" + queryString).then((data) => {
            data.forEach((element) => {
                this.createCommentDom(element);
            });
        });
    },
    getData : async function (url = "", data = {}) {
        // 옵션 기본 값은 *로 강조
        const response = await fetch(url, {
            method: "GET", // *GET, POST, PUT, DELETE 등
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json",
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            // body: JSON.stringify(data), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
        });
        return response.json(); // JSON 응답을 네이티브 JavaScript 객체로 파싱
    },
}