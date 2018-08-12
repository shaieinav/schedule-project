import React, { Component } from 'react';
import Select from 'react-select';
import { Button, ButtonGroup, Container, Table, Row, Col, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavBar from '../AppNavBar';
import { Link } from 'react-router-dom';

class CourseList extends Component {

    constructor(props) {

        super(props);

        this.state = {
            courses: [],
            isLoading: true,
            query: '',
            selectedOption: null,
            // courseGroups: []
        };

        this.updateQuery = this.updateQuery.bind(this);
        this.handleChange = this.handleChange.bind(this);
        // this.handleChangeCourseGroup = this.handleChangeCourseGroup.bind(this);
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {

        this.setState({isLoading: true});
        this.setState({filter: []});

        fetch('courses')
            .then(response => response.json())
            .then(data => this.setState({
                courses: data,
                isLoading: false
            }));
    }

    // componentDidUpdate(prevState) {
    //     if (this.state.selectedOption !== prevState.selectedOption) {
    //         fetch(`courses/${courseNum}/${courseGroups}`)
    //             .then(response => response.json())
    //             .then(data => this.setState({
    //                 courseGroups: data
    //             }));
    //     }
    // }

    async remove(courseNum) {

        await fetch(`courses/${courseNum}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedCourses = [...this.state.courses].filter(i => i.courseNum !== courseNum);
            this.setState({courses: updatedCourses});
        });
    }

    updateQuery = (query) => {
        this.setState(() => ({
            query: query
        }))
    }

    handleChange = (selectedOption) => {
        this.setState({ selectedOption });
        console.log(`Option selected:`, selectedOption);
    }

    // handleChangeCourseGroup = (selectedOption) => {
    //     this.setState({ selectedOption });
    //     console.log(`Option selected:`, selectedOption);
    // }

    render() {

        const { courses, isLoading, query } = this.state;
        const filteredCourses =
            query === ''
            ? courses
            : courses.filter((course) => (
                course.courseName.toLowerCase().includes(query.toLowerCase())
            ))

        if (isLoading) {
            return <p>Loading... Please Wait</p>;
        }

        const courseList = filteredCourses.map(course => {
            return (
                <tr key={course.courseNum}>
                    <td style={{whiteSpace: 'nowrap'}}>{course.courseNum}</td>
                    <td style={{whiteSpace: 'nowrap'}}>{course.courseName}</td>
                    <td style={{whiteSpace: 'nowrap'}}>{course.courseCredit}</td>
                    <td>
                        <ButtonGroup>
                            <Button size="sm" color="primary" tag={Link} to={"/courses/" + course.courseNum}>Edit</Button>
                            <Button size="sm" color="danger" onClick={() => this.remove(course.courseNum)}>Delete</Button>
                        </ButtonGroup>
                    </td>
                </tr>
            )
        });

        const courseOptions = filteredCourses.map(function (course) {
            return { value: course.courseNum, label: course.courseName };
        })
        // const courseGroupOptions = courseGroups.map(function (courseGroup) {
        //     return { value: courseGroup.courseGroupId, label: courseGroup.courseGroupNum };
        // })

        return (
            <div>
                <AppNavBar/>
                <Container fluid>

                    <div className="float-right">
                        <Button color="success" tag={Link} to="/courses/new">Add Group</Button>
                    </div>

                    <Row>
                        <h3>My Courses</h3>
                    </Row>

                    <Row>
                        <Col xs="3">
                            <div className="search-course">
                                <Container>
                                    <Form>

                                        <Select options={courseOptions}
                                                valueKey='courseNum'
                                                lableKey='courseName'
                                                placeholder={'Search for a course'}
                                                autoComplete='courseName'
                                                onChange={this.handleChange}
                                        />

                                        {/*<Select options={courseGroupOptions}*/}
                                                {/*valueKey='courseNum'*/}
                                                {/*lableKey='courseName'*/}
                                                {/*placeholder={'Search for a course'}*/}
                                                {/*autoComplete='courseName'*/}
                                                {/*onChange={this.handleChangeCuorseGroup}*/}
                                        {/*/>*/}

                                        {/*<FormGroup>*/}
                                            {/*<Label for='courseNum'>Choose Course Name</Label>*/}
                                            {/*<Input type='text' name='courseNum' id='courseNum'*/}
                                                   {/*className='search-courses' value={this.state.query}*/}
                                                   {/*placeholder={'Search for a course'} autoComplete='courseNum'*/}
                                                   {/*onChange={(event) => this.updateQuery(event.target.value)}*/}
                                            {/*/>*/}
                                        {/*</FormGroup>*/}

                                        <FormGroup>
                                            <Label for='courseNum'>Choose Semester</Label>
                                            <Input type='text' name='semester' id='semester'
                                                   className='choose-semester' value={this.state.query}
                                                   placeholder={'Choose Semester'} autoComplete='semester'
                                                   onChange={(event) => this.updateQuery(event.target.value)}
                                            />
                                        </FormGroup>
                                        <FormGroup>
                                            <Label for='courseNum'>Choose a Course Group</Label>
                                            <Input type='text' name='courseGroup' id='courseGroup'
                                                   className='choose-course-group' value={this.state.query}
                                                   placeholder={'Choose Course Group'} autoComplete='courseGroup'
                                                   onChange={(event) => this.updateQuery(event.target.value)}
                                            />
                                        </FormGroup>
                                    </Form>
                                </Container>
                            </div>
                        </Col>
                        <Col xs="9">
                            <div className="courses">
                                <Table className="mt-4">
                                    <thead>
                                        <tr>
                                            <th width="20%">Course Number</th>
                                            <th width="50%">Course Name</th>
                                            <th width="20%">Course Credit</th>
                                            <th width="10%">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {courseList}
                                    </tbody>
                                </Table>
                            </div>
                        </Col>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default CourseList;