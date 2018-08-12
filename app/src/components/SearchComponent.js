import React from 'react';
import Select from 'react-select';
import {Container, Form, FormGroup, Input, Label} from 'reactstrap';

const searchComponent = (props) => {

    const courseOptions = props.filteredCourses.map(course => {
            return { value: course.courseNum, label: course.courseName };
        })

    return (
        <div className="search-course">
            <Container>
                <Form>
                    <FormGroup>
                        <Label for="courseNum">Choose Course Number or Name</Label>
                        <Select options={courseOptions}
                                // value={props.selectedOption}
                                // value={this.typeId}
                                valueKey='courseNum'
                                lableKey='courseName'
                                placeholder={'Search for a course'}
                                autoComplete='courseName'
                                onChange={(courseObject) => props.handleChosenCourse(courseObject)}
                                // onChange={(courseObject) => props.handleChange(courseObject)}
                                // onClick={() => props.handleChosenCourse(course.courseNum)}
                        />
                    </FormGroup>
{/*                    <FormGroup>
                        <Label for="courseNum">Choose Semester</Label>
                        <Input type="text" name="semester" id="semester"
                               value={props.search || ''}
                               placeholder={"Choose Semester"}
                               onChange={props.updateQuery} autoComplete="semester"
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label for="courseNum">Choose a Course Group</Label>
                        <Input type="text" name="courseGroup" id="courseGroup"
                               value={props.search || ''}
                               placeholder={"Choose Course Group"}
                               onChange={props.updateQuery} autoComplete="courseGroup"
                        />
                    </FormGroup>*/}
                </Form>
            </Container>
        </div>
    )
}

export default searchComponent;