import {
    reqListTeachers,
    reqSearchTeachers,
    reqListRoles,

    reqListCourses,
    reqTeacherNames,
    reqSearchCourses,

    reqSearchStudents
} from '../api'

import {
    RECEIVE_TEACHERMSGS,
    RECEIVE_SEARCH_TEACHERSMSGS,
    RECEIVE_ROLES,

    RECEIVE_COURSEMSGS,
    RECEIVE_TEACHER_NAMES,
    RECEIVE_SEARCH_COURSESMSGS,

    RECEIVE_SEARCH_SUTDENTSMSGS,

    RECEIVE_USER_INFO
} from './mutation-types'

export default {

    //异步获取老师信息
    async getTeacher({ commit }, pageNum) {
        const result = await reqListTeachers({ pageNum })
        if (result.status === 200) {
            const teacherMsgs = result.data
            commit(RECEIVE_TEACHERMSGS, { teacherMsgs })
        }
    },

    //异步搜索获取老师信息
    async searchTeachers({ commit }, keyword) {
        const result = await reqSearchTeachers(keyword)
        if (result.status === 200) {
            const searchTeachersMsgs = result.data
            commit(RECEIVE_SEARCH_TEACHERSMSGS, { searchTeachersMsgs })
        } else {
            alert(result.msg);
            const searchTeachersMsgs = ""
            commit(RECEIVE_SEARCH_TEACHERSMSGS, { searchTeachersMsgs })
        }

    },

    //异步获取角色名
    async getRoles({ commit }) {
        const result = await reqListRoles()
        if (result.status === 200) {
            const roles = result.data
            commit(RECEIVE_ROLES, { roles })
        }
    },




    //异步获取所有课程信息
    async getCourse({ commit }, version, pageNum) {
        const result = await reqListCourses(version, pageNum)
        if (result.status === 200) {
            const courseMsgs = result.data
            commit(RECEIVE_COURSEMSGS, { courseMsgs })
        }
    },

    //添加老师时，异步获取所有老师名
    async getTeacherNames({ commit }) {
        const result = await reqTeacherNames()
        if (result.status === 200) {
            const teacherNames = result.data
            commit(RECEIVE_TEACHER_NAMES, { teacherNames })
        }
    },

    //异步获取搜索到的课程信息
    async searchCourses({ commit }, name) {
        const result = await reqSearchCourses({ name })
        if (result.status === 200) {
            const searchCoursesMsgs = result.data
            commit(RECEIVE_SEARCH_COURSESMSGS, { searchCoursesMsgs })
        } else {
            alert(result.msg);
            const searchCoursesMsgs = ""
            commit(RECEIVE_SEARCH_COURSESMSGS, { searchCoursesMsgs })
        }
    },




    //异步获取搜索到的学生信息
    async searchStudents({ commit }, name) {
        const result = await reqSearchStudents({ name })
        if (result.status === 200) {
            const searchStudentsMsgs = result.data
            commit(RECEIVE_SEARCH_SUTDENTSMSGS, { searchStudentsMsgs })
        } else {
            alert(result.msg);
            const searchStudentsMsgs = ""
            commit(RECEIVE_SEARCH_SUTDENTSMSGS, { searchStudentsMsgs })
        }
    },


    // 同步记录用户信息
    recordUser({ commit }, userInfo) {
        commit(RECEIVE_USER_INFO, { userInfo })
    },

}