import ajax from './ajax'

const BASE_URL = '/api'

/**
 * 教师管理
 */
//列出所有老师
export const reqListTeachers = ({ pageNum }) => ajax(`${BASE_URL}/authentication/listTeacher`, { pageNum }, 'POST');

//添加老师
export const reqAddTeacher = ({ name, teacherNum, rolename }) => ajax(`${BASE_URL}/authentication/addTeacher`, { name, teacherNum, rolename }, 'POST');

//添加老师时，列出所有角色名
export const reqListRoles = () => ajax(`${BASE_URL}authentication/listRole`)

//删除老师
export const reqDeleteTeacher = (id) => ajax(`${BASE_URL}/authentication/deleteTeacher`, id, 'POST')

//修改老师
export const reqUpdateTeacher = ({ id, name, teacherNum, rolename }) => ajax(`${BASE_URL}/authentication/updateTeacher`, { id, name, teacherNum, rolename }, 'POST');

//根据教师名字模糊查询老师
export const reqSearchTeachers = ({ keyword }) => ajax(BASE_URL + '/authentication/findTeacher', { keyword }, 'POST')



/**
 * 课程管理
 */
//列出所有课程（带分页）
export const reqListCourses = (version, pageNum) => ajax(`${BASE_URL}/authentication/listCourseByVersion`, { version, pageNum }, 'POST')

//添加课程
export const reqAddCourse = ({ courseNum, name, theoryTime, experTime, credit, property, type, version, teacherName }) => ajax(`${BASE_URL}/authentication/addCourse`, { courseNum, name, theoryTime, experTime, credit, property, type, version, teacherName }, 'POST')

//添加课程时，列出所有老师
export const reqTeacherNames = () => ajax(`${BASE_URL}authentication/listAllTeacher`)

//删除课程
export const reqDeleteCourse = (id) => ajax(`${BASE_URL}/authentication/deleteCourse`, id, 'POST')

//修改课程
export const reqUpdateCourse = ({ id, courseNum, name, theoryTime, experTime, credit, property, type, version, teacherName }) => ajax(`${BASE_URL}/authentication/updateCourse`, { id, courseNum, name, theoryTime, experTime, credit, property, type, version, teacherName }, 'POST');

//查询课程
export const reqSearchCourses = ({ name }) => ajax(BASE_URL + '/authentication/findCourse', { name }, 'POST')



/**
 * 学生管理
 */
//列出所有学生信息
export const reqListStudents = (grade, pageNum) => ajax(`${BASE_URL}/authentication/listStudent`, { grade, pageNum }, 'POST')

//添加学生
export const reqAddStudent = ({ studentNum, name, major, className, grade }) => ajax(`${BASE_URL}/authentication/addStudent`, { studentNum, name, major, className, grade }, 'POST')

//删除学生
export const reqDeleteStudent = ({ id }) => ajax(`${BASE_URL}/authentication/deleteStudent`, { id }, 'POST')

//修改学生
export const reqUpdateStudent = ({ studentNum, name, major, className, grade }) => ajax(`${BASE_URL}/authentication/updateStudent`, { studentNum, name, major, className, grade }, 'POST');

//查询学生
export const reqSearchStudents = ({ name }) => ajax(BASE_URL + '/authentication/listStudentByName', { name }, 'POST')




/**
 * 选课管理
 */
//列出所有课程（不带分页）
export const reqCourses = () => ajax(`${BASE_URL}/authentication/listCourse`)

//获取学生详细信息
export const reqListAllTeacherSelcourse = (courseId, teacherId, term, version, leaderName) => ajax(`${BASE_URL}/authentication/listAllTeacherSelcourse`, { courseId, teacherId, term, version, leaderName }, 'POST')

//点击添加，获取所有学生信息
export const reqClickTeacherSelcourse = (grade) => ajax(`${BASE_URL}/authentication/listAllStudent`, { grade }, 'POST')

//保存选中的学生
export const reqSaveStudent = (courseId, teacherId, leaderId, term, version, studentIdList, leaderName) => ajax(`${BASE_URL}/authentication/addSelcourse`, { courseId, teacherId, leaderId, term, version, studentIdList, leaderName }, 'POST')

//删除当前学生
export const reqDeleteThisStudent = (studentId, courseId, version) => ajax(`${BASE_URL}/authentication/deleteSelcourse`, { studentId, courseId, version }, 'POST')





/**
 * 指标管理
 */
//列出所有指标
export const reqListIndications = ({ version }) => ajax(`${BASE_URL}/authentication/listIndication`, { version }, 'POST')

//添加指标
export const reqAddIndication = ({ indicationNum, content, version }) => ajax(`${BASE_URL}/authentication/addIndication`, { indicationNum, content, version }, 'POST')

//删除指标
export const reqDeleteIndication = ({ id }) => ajax(`${BASE_URL}/authentication/deletaIndication`, { id }, 'POST')

//修改指标
export const reqUpdateIndication = ({ id, indicationNum, content, version }) => ajax(`${BASE_URL}/authentication/updateIndication`, { id, indicationNum, content, version }, 'POST');




/**
 * 目标管理
 */
//列出所有指标
export const reqListTargets = ({ version, courseName }) => ajax(`${BASE_URL}/authentication/listAllTarget`, { version, courseName }, 'POST')

//添加指标
export const reqAddTarget = (targerNum, content, courseId, version) => ajax(`${BASE_URL}/authentication/addTarget`, { targerNum, content, courseId, version }, 'POST')

//删除指标
export const reqDeleteTarget = ({ id }) => ajax(`${BASE_URL}/authentication/deleteTarget`, { id }, 'POST')

//修改指标
export const reqUpdateTarget = (id, targerNum, content, courseId, version) => ajax(`${BASE_URL}/authentication/updateTarget`, { id, targerNum, content, courseId, version }, 'POST');




/**
 * 课程-指标点支撑度矩阵设置
 */
//添加
export const reqAddIndicationCourse = (indicationId, courseIdList, weightList) => ajax(`${BASE_URL}/authentication/addIndicationCourse`, { indicationId, courseIdList, weightList }, 'POST')

//获取当前课程名和权重
export const reqListAllIndicationCourseByIndicationId = (indicationId) => ajax(`${BASE_URL}/authentication/listAllIndicationCourseByIndicationId`, { indicationId }, 'POST')




/**
 * 课程考核设置
 */
//查询，列出所有考核方式和权重
export const reqListExams = (courseId, version) => ajax(`${BASE_URL}/authentication/listAllExamByCourseIdAndVersion`, { courseId, version }, 'POST')

//点击添加按钮，获取所有的考核方式
export const reqExamTypes = () => ajax(`${BASE_URL}/authentication/listAllExamType`)

//保存添加的数据
export const reqAddExams = (courseId, version, examTypeIdList, ratioList) => ajax(`${BASE_URL}/authentication/addExam`, { courseId, version, examTypeIdList, ratioList }, 'POST')


/**
 * 课程考核比例设置
 */
//获取目标id
export const reqTargets = () => ajax(`${BASE_URL}/authentication/listAllTargetByNo`)

//查询，列出信息
export const reqListExamDetails = (targetId, examtypeId, version) => ajax(`${BASE_URL}/authentication/listAllExamDetailByExamTypeOrTarget`, { targetId, examtypeId, version }, 'POST')

//保存添加数据
export const reqAddExamDetails = (examtypeId, targetId, version, nameList, vualeList) => ajax(`${BASE_URL}/authentication/addExamdetail`, { examtypeId, targetId, version, nameList, vualeList }, 'POST')



/**
 * 成绩管理
 */
//上传文件
export const reqUpload = ({ }) => ajax(`${BASE_URL}/authentication/upLoad`, {}, 'POST')


//下载文件
export const reqDownload = (courseName, version) => ajax(`${BASE_URL}/authentication/xiazai`, { courseName, version }, 'POST')





/**
 * 课程总体达成度
 */
//查询，列出信息
export const reqTotalAchievement = (courseName, version) => ajax(`${BASE_URL}/authentication/checkTotaldachengdu`, { courseName, version }, 'POST')



/**
 * 毕业个体达成度
 */
//查询
export const reqGraduateIndividual = (courseName, version) => ajax(`${BASE_URL}/authentication/chechPersonalDachengdu`, { courseName, version }, 'POST')



/**
 * 毕业要求达成度
 */
//查询
export const reqGraduateRequirement = (version) => ajax(`${BASE_URL}/authentication/checkGraduationDachengdu`, { version }, 'POST')






/**
 * 登录
 */
export const reqLogin = (teacherNum, password) => ajax(`${BASE_URL}/authentication/login`, { teacherNum, password }, 'POST')

/**
 * 退出登录
 */
export const reqLoginOut = () => ajax(`${BASE_URL}/authentication/loginout`)
