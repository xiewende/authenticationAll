import Vue from 'vue'

import {
    RECEIVE_TEACHERMSGS,
    RECEIVE_SEARCH_TEACHERSMSGS,
    RECEIVE_ROLES,

    RECEIVE_COURSEMSGS,
    RECEIVE_SEARCH_COURSESMSGS,
    RECEIVE_TEACHER_NAMES,

    RECEIVE_SEARCH_SUTDENTSMSGS,

    RECEIVE_USER_INFO
} from './mutation-types'

export default {
    [RECEIVE_TEACHERMSGS](state, { teacherMsgs }) {
        state.teacherMsgs = teacherMsgs
    },

    [RECEIVE_SEARCH_TEACHERSMSGS](state, { searchTeachersMsgs }) {
        state.searchTeachersMsgs = searchTeachersMsgs
    },

    [RECEIVE_ROLES](state, { roles }) {
        state.roles = roles
    },




    [RECEIVE_COURSEMSGS](state, { courseMsgs }) {
        state.courseMsgs = courseMsgs
    },

    [RECEIVE_SEARCH_COURSESMSGS](state, { searchCoursesMsgs }) {
        state.searchCoursesMsgs = searchCoursesMsgs
    },

    [RECEIVE_TEACHER_NAMES](state, { teacherNames }) {
        state.teacherNames = teacherNames
    },



    [RECEIVE_SEARCH_SUTDENTSMSGS](state, { searchStudentsMsgs }) {
        state.searchStudentsMsgs = searchStudentsMsgs
    },

    [RECEIVE_USER_INFO] (state, {userInfo}) {
        state.userInfo = userInfo
      },
}