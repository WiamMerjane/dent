import React, { useState } from 'react';
import axios from 'axios';

import { MDBBtn, MDBContainer, MDBCard, MDBCardBody, MDBCardImage, MDBRow, MDBCol, MDBIcon, MDBInput } from 'mdb-react-ui-kit';

import dentalImage from 'd1.jpg';

export const AuthContext = React.createContext();

const StudentLogin = ({ onLogin }) => {
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [loginSuccess, setLoginSuccess] = useState(false);
    const [user, setUser] = useState(null);

    const handleUsernameChange = (e) => {
      setUserName(e.target.value);
    };

    const handlePasswordChange = (e) => {
      setPassword(e.target.value);
    };

    const handleLogin = async (userData) => {
        setUser(userData);
        setLoginSuccess(true);
        localStorage.setItem('studentId', userData.id);
    };

    const getUserData = async () => {
        const user = localStorage.getItem('studentId');

        if (user) {
            const response = await axios.get(`http://localhost:8082/api/students/${user}`);
            return response.data;

        }
        return null;
    };

    const updateUserData = async (updatedData) => {
        setUser(updatedData);
    };

    const handleSubmit = async (e) => {
      e.preventDefault();

      try {
        const response = await axios.post('http://localhost:8082/api/students/login', {
          userName: userName,
          password: password,
        });

        if (response.status === 200) {
          // Login successful
          const studentData = response.data;
          onLogin(studentData);
          setLoginSuccess(true);
          await handleLogin(studentData)
        }
      } catch (error) {
        // Login failed, handle the error
        console.error('Login failed', error.response?.data);
        setErrorMessage('Login failed. Please check your credentials.');
      }
    };

  if (loginSuccess) {
    // Redirect to another page upon successful login
    // You can replace '/dashboard' with the desired URL
    window.location.href = '/student/dashboard';
  }

  return (
    <AuthContext.Provider value={{ getUserData, updateUserData }}>
      <MDBContainer className="my-5">
        <MDBCard>
          <MDBRow className="g-0">
            <MDBCol md="6">
              <MDBCardImage src={dentalImage} alt="Dental Logo" className="rounded-start w-100" />
            </MDBCol>
            <MDBCol md="6">
              <MDBCardBody className="d-flex flex-column">
                
                <h5 className="fw-normal my-4 pb-3" style={{ letterSpacing: '1px' }}>
                  Sign into your account
                </h5>
                <h6>Student</h6>
                <MDBInput
                  wrapperClass="mb-4"
                  label="Username"
                  id="formControlLg"
                  type="text"
                  size="lg"
                  value={userName}
                  onChange={handleUsernameChange}
                />
                <MDBInput
                  wrapperClass="mb-4"
                  label="Password"
                  id="formControlLg"
                  type="password"
                  size="lg"
                  value={password}
                  onChange={handlePasswordChange}
                />
                <MDBBtn className="mb-4 px-5" color="dark" size="lg" onClick={handleSubmit}>
                  Login
                </MDBBtn>
               
            
               
              </MDBCardBody>
            </MDBCol>
          </MDBRow>
        </MDBCard>
      </MDBContainer>
    </AuthContext.Provider>
  );
};
export default StudentLogin;

// CSS styles directly in the component
const styles = `
.student-login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  margin-top: 50px;
}

.error-message {
  color: red;
  margin-top: 10px;
}

.input-container {
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

button {
  background-color: #4caf50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
`;

// Inject the styles into the head of the document
const styleElement = document.createElement('style');
styleElement.appendChild(document.createTextNode(styles));
document.head.appendChild(styleElement);
