const API_BASE = '';

const logEl = document.getElementById('log');

function log(message, isError) {
    const entry = document.createElement('div');
    entry.className = 'entry ' + (isError ? 'err' : 'ok');
    entry.textContent = message;
    logEl.prepend(entry);
}

// 백엔드 ErrorResponse({status, code, message, timestamp})를 그대로 파싱해서 보여준다.
async function request(method, url, body) {
    const options = { method, headers: {} };
    if (body !== undefined) {
        options.headers['Content-Type'] = 'application/json';
        options.body = JSON.stringify(body);
    }

    const response = await fetch(API_BASE + url, options);
    const text = await response.text();
    const data = text ? JSON.parse(text) : null;

    if (!response.ok) {
        const errorMessage = data ? `[${data.code}] ${data.message}` : `HTTP ${response.status}`;
        log(`${method} ${url} -> ${response.status} ${errorMessage}`, true);
        throw new Error(errorMessage);
    }

    log(`${method} ${url} -> ${response.status}`, false);
    return data;
}

// ---------- Team ----------

const teamForm = document.getElementById('team-form');
const teamIdInput = document.getElementById('team-id');
const teamNameInput = document.getElementById('team-name');
const teamSubmitBtn = document.getElementById('team-submit-btn');
const teamCancelBtn = document.getElementById('team-cancel-btn');
const teamTableBody = document.getElementById('team-table-body');
const teamSelect = document.getElementById('member-team');

function renderTeams(teams) {
    teamTableBody.innerHTML = '';
    teamSelect.innerHTML = '<option value="">소속 팀 없음</option>';

    teams.forEach(team => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${team.id}</td>
            <td>${team.name}</td>
            <td>${team.memberCount}</td>
            <td>
                <button data-action="edit-team" data-id="${team.id}" data-name="${team.name}">수정</button>
                <button data-action="delete-team" data-id="${team.id}">삭제</button>
            </td>`;
        teamTableBody.appendChild(row);

        const option = document.createElement('option');
        option.value = team.id;
        option.textContent = team.name;
        teamSelect.appendChild(option);
    });
}

async function loadTeams(keyword) {
    const url = keyword ? `/teams/search?name=${encodeURIComponent(keyword)}` : '/teams';
    try {
        renderTeams(await request('GET', url));
    } catch (e) { /* 에러는 이미 로그에 표시됨 */ }
}

teamForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = teamIdInput.value;
    const body = { name: teamNameInput.value };
    try {
        if (id) {
            await request('PUT', `/teams/${id}`, body);
        } else {
            await request('POST', '/teams', body);
        }
        resetTeamForm();
        await loadTeams();
    } catch (e) { /* 에러는 이미 로그에 표시됨 */ }
});

teamTableBody.addEventListener('click', async (e) => {
    const btn = e.target.closest('button');
    if (!btn) return;
    const { action, id, name } = btn.dataset;

    if (action === 'edit-team') {
        teamIdInput.value = id;
        teamNameInput.value = name;
        teamSubmitBtn.textContent = '팀 수정';
        teamCancelBtn.classList.remove('hidden');
    } else if (action === 'delete-team') {
        try {
            await request('DELETE', `/teams/${id}`);
            await loadTeams();
            await loadMembers();
        } catch (e) { /* 에러는 이미 로그에 표시됨 */ }
    }
});

teamCancelBtn.addEventListener('click', resetTeamForm);

function resetTeamForm() {
    teamForm.reset();
    teamIdInput.value = '';
    teamSubmitBtn.textContent = '팀 생성';
    teamCancelBtn.classList.add('hidden');
}

document.getElementById('team-search-form').addEventListener('submit', (e) => {
    e.preventDefault();
    loadTeams(document.getElementById('team-search-input').value);
});
document.getElementById('team-search-reset').addEventListener('click', () => {
    document.getElementById('team-search-input').value = '';
    loadTeams();
});

// ---------- Member ----------

const memberForm = document.getElementById('member-form');
const memberIdInput = document.getElementById('member-id');
const memberNameInput = document.getElementById('member-name');
const memberAgeInput = document.getElementById('member-age');
const memberPartInput = document.getElementById('member-part');
const memberSubmitBtn = document.getElementById('member-submit-btn');
const memberCancelBtn = document.getElementById('member-cancel-btn');
const memberTableBody = document.getElementById('member-table-body');

function renderMembers(members) {
    memberTableBody.innerHTML = '';
    members.forEach(member => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.age}</td>
            <td>${member.part}</td>
            <td>${member.teamName ?? '-'}</td>
            <td>
                <button data-action="edit-member"
                        data-id="${member.id}" data-name="${member.name}"
                        data-age="${member.age}" data-part="${member.part}"
                        data-team-id="${member.teamId ?? ''}">수정</button>
                <button data-action="delete-member" data-id="${member.id}">삭제</button>
            </td>`;
        memberTableBody.appendChild(row);
    });
}

async function loadMembers(keyword) {
    const url = keyword ? `/members/search?keyword=${encodeURIComponent(keyword)}` : '/members';
    try {
        renderMembers(await request('GET', url));
    } catch (e) { /* 에러는 이미 로그에 표시됨 */ }
}

memberForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = memberIdInput.value;
    const body = {
        name: memberNameInput.value,
        age: Number(memberAgeInput.value),
        part: memberPartInput.value,
        teamId: teamSelect.value ? Number(teamSelect.value) : null
    };
    try {
        if (id) {
            await request('PUT', `/members/${id}`, body);
        } else {
            await request('POST', '/members', body);
        }
        resetMemberForm();
        await loadMembers();
        await loadTeams();
    } catch (e) { /* 에러는 이미 로그에 표시됨 */ }
});

memberTableBody.addEventListener('click', async (e) => {
    const btn = e.target.closest('button');
    if (!btn) return;
    const { action, id, name, age, part, teamId } = btn.dataset;

    if (action === 'edit-member') {
        memberIdInput.value = id;
        memberNameInput.value = name;
        memberAgeInput.value = age;
        memberPartInput.value = part;
        teamSelect.value = teamId || '';
        memberSubmitBtn.textContent = '멤버 수정';
        memberCancelBtn.classList.remove('hidden');
    } else if (action === 'delete-member') {
        try {
            await request('DELETE', `/members/${id}`);
            await loadMembers();
            await loadTeams();
        } catch (e) { /* 에러는 이미 로그에 표시됨 */ }
    }
});

memberCancelBtn.addEventListener('click', resetMemberForm);

function resetMemberForm() {
    memberForm.reset();
    memberIdInput.value = '';
    memberSubmitBtn.textContent = '멤버 생성';
    memberCancelBtn.classList.add('hidden');
}

document.getElementById('member-search-form').addEventListener('submit', (e) => {
    e.preventDefault();
    loadMembers(document.getElementById('member-search-input').value);
});
document.getElementById('member-search-reset').addEventListener('click', () => {
    document.getElementById('member-search-input').value = '';
    loadMembers();
});

// ---------- init ----------

loadTeams();
loadMembers();
