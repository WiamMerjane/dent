import React, { useState, useEffect } from 'react';
import axios from 'axios';

import { Card, CardHeader, CardBody, CardTitle, Row, Col, Table, Form, FormGroup, Label, Input } from 'reactstrap';

function Tables() {
  const [students, setStudents] = useState([]);
  const [newStudent, setNewStudent] = useState({ userName: '', firstName: '', lastName: '', number: '' });
  const [editingStudent, setEditingStudent] = useState(null);
  const [studentPWs, setStudentPWs] = useState([]);
  const [selectedStudent, setSelectedStudent] = useState(null);
  const filteredPWs = studentPWs.filter((studentPW) => studentPW.student.id === parseInt(selectedStudent, 10));



  const handleStudentSelect = (studentId) => {
    setSelectedStudent(studentId);
  };
 

  useEffect(() => {
    fetchStudents();
    fetchStudentPWs(); // Appel pour récupérer les données des relations StudentPW
    //fetchStudents();
  }, []);

  const fetchStudents = async () => {
    try {
      const response = await axios.get('http://localhost:8082/api/students');
      setStudents(response.data);
    } catch (error) {
      console.error('Error fetching students:', error);
    }
  };

  const createStudent = async () => {
    try {
      await axios.post('http://localhost:8082/api/students', newStudent);
      setNewStudent({ userName: '', firstName: '', lastName: '', number: '' });
      fetchStudents();
    } catch (error) {
      console.error('Error creating student:', error);
    }
  };

  const updateStudent = async () => {
    try {
      await axios.put(`http://localhost:8082/api/students/${editingStudent.id}`, editingStudent);
      setEditingStudent(null);
      fetchStudents();
    } catch (error) {
      console.error('Error updating student:', error);
    }
  };

  const deleteStudent = async (id) => {
    try {
      await axios.delete(`http://localhost:8082/api/students/${id}`);
      fetchStudents();
    } catch (error) {
      console.error('Error deleting student:', error);
    }
  };

  const handleEdit = (student) => {
    setEditingStudent({ ...student });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
  
    if (editingStudent !== null) {
      // If in edit mode, update the editingStudent state
      setEditingStudent({
        ...editingStudent,
        [name]: value,
      });
    } else {
      // If not in edit mode, update the newStudent state
      setNewStudent({
        ...newStudent,
        [name]: value,
      });
    }
  };

  
  
    const [student, setStudent] = useState([]);



 

    const fetchStudentPWs = async () => {
      try {
        const response = await axios.get('http://localhost:8082/api/students/pws');
        console.log('Fetched StudentPWs:', response.data); // Add this log
        setStudentPWs(response.data);
      } catch (error) {
        console.error('Error fetching StudentPWs:', error);
      }
    };
    
    
    useEffect(() => {
      console.log('Students:', students);
    }, [students]);
    
    useEffect(() => {
      console.log('StudentPWs:', studentPWs);
    }, [studentPWs]);
  
  
    const renderStudentPWs = () => {
      if (selectedStudent !== null) {
        console.log('Selected Student:', selectedStudent);
        console.log('All Student PWs:', studentPWs);
    
        const filteredPWs = studentPWs.filter((studentPW) => studentPW.student.id === parseInt(selectedStudent, 10));
        console.log('Filtered PWs:', filteredPWs);
    
        return (
          <div>
            {filteredPWs.map((studentPW) => (
              <div key={studentPW.id}>
                <p>Title: {studentPW.pw.titre}</p>
                <p>Objectif: {studentPW.pw.objectif}</p>
                <hr />
              </div>
            ))}
          </div>
        );
      }
      return null;
    };
    

  return (
    <>
      <div className="content">
        <Row>
          <Col md="12">
            <Card>
              <CardHeader>
                <CardTitle tag="h4">Student Table</CardTitle>
              </CardHeader>
              <CardBody>
              <Form>
  <FormGroup>
    <Label for="userName">Username</Label>
    <Input type="text" name="userName" id="userName" value={editingStudent?.userName || newStudent.userName || ''} onChange={handleInputChange} />
  </FormGroup>
  <FormGroup>
    <Label for="firstName">First Name</Label>
    <Input type="text" name="firstName" id="firstName" value={editingStudent?.firstName || newStudent.firstName || ''} onChange={handleInputChange} />
  </FormGroup>
  <FormGroup>
    <Label for="lastName">Last Name</Label>
    <Input type="text" name="lastName" id="lastName" value={editingStudent?.lastName || newStudent.lastName || ''} onChange={handleInputChange} />
  </FormGroup>
  <FormGroup>
    <Label for="number">Number</Label>
    <Input type="text" name="number" id="number" value={editingStudent?.number || newStudent.number || ''} onChange={handleInputChange} />
  </FormGroup>
  {editingStudent ? (
    <button type="button" onClick={updateStudent}>
      Update Student
    </button>
  ) : (
    <button type="button" onClick={createStudent}>
      Add Student
    </button>
  )}
</Form>
                <Table responsive>
                  <thead className="text-primary">
                    <tr>
                      <th>Username</th>
                      <th>Name</th>
                      <th>Number</th>
                      <th className="text-right">Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    {students.map((student) => (
                      <tr key={student.id}>
                        <td>{student.userName}</td>
                        <td>{`${student.firstName} ${student.lastName}`}</td>
                        <td>{student.number}</td>
                        <td className="text-right">
                          <button onClick={() => handleEdit(student)}>Edit</button>
                          <button onClick={() => deleteStudent(student.id)}>Delete</button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </Table>

                <div>
          <h3>Student PWs:</h3>
                <Label for="studentDropdown">Select Student:</Label>
                <Input
                  type="select"
                  name="studentDropdown"
                  id="studentDropdown"
                  value={selectedStudent}
                  onChange={(e) => handleStudentSelect(e.target.value)}
                >
                  <option value="" disabled>Select a student</option>
                  {students.map((student) => (
                    <option key={student.id} value={student.id}>
                      {`${student.firstName} ${student.lastName}`}
                    </option>
                  ))}
                </Input>
                {renderStudentPWs()}
              </div>
              </CardBody>
            </Card>
          </Col>
          <Col md="12">
          <CardBody>
        
                </CardBody>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Tables;
