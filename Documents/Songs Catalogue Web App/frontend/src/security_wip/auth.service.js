// import authHeader from "./auth-header";
// const AUTH_URL = "http://localhost:8080/auth";
// const register = (username, password) => {
//     return fetch(AUTH_URL + "/register", {
//         method: 'POST',
//         headers: {
//             "Content-Type": "application/json",
//             "X-Requested-With": "XMLHttpRequest",
//             Authorization: 'Bearer '
//         },
//         body: JSON.stringify({
//             username: username,
//             password: password
//         })
//     });
//
// };
//
// const login = (username, password) => {
//     return fetch(AUTH_URL + "/login", {
//         method: 'POST',
//         headers: {
//             "Content-Type": "application/json",
//             "X-Requested-With": "XMLHttpRequest",
//             Authorization: 'Bearer '
//         },
//         body: JSON.stringify({
//             username: username,
//             password: password
//         })
//     }).then((response) => {
//             if (response.accessToken) {
//                 localStorage.setItem("user", JSON.stringify(response.data));
//             }
//
//             // console.log(response);
//             return response; //TODO: Not sure if needed, maybe delete later
//     });
// };
//
// const logout = () => {
//     localStorage.removeItem("user");
// };
//
// const getCurrentUser = () => {
//     return JSON.parse(localStorage.getItem("user"));
// };
//
// const AuthService = {
//     register,
//     login,
//     logout,
//     getCurrentUser,
// };
//
// export default AuthService;