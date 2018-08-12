import React from 'react';
import Select from 'react-select';
import {Container, Form, FormGroup, Label} from 'reactstrap';

const searchComponent = (props) => {

    const courseOptions = props.filteredCourses.map(course => {
        return {value: course.courseNum, label: course.courseName};
    });
    //console.log(courseOptions);

    //console.log(props.selectedOption);
    let groupOptions = props.selectedOption.map(group => group.courseGroupId)
        .map(group => {
            return {value: group.groupNum, label: (`${group.groupLocation}, ${group.groupNum}`)};
        });

    //console.log(groupOptions);

    return (
        <div className="search-course">
            <Container>
                <Form>
                    <FormGroup>
                        <Label for="courseNum">Choose Course Number or Name</Label>
                        <Select options={courseOptions}
                                valueKey='courseNum'
                                lableKey='courseName'
                                placeholder={'Search for a course'}
                                autoComplete='courseName'
                                onChange={(courseObject) => props.handleChosenCourse(courseObject)}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label for="courseNum">Choose a Course Group</Label>
                        <Select
                            options={groupOptions}
                        />
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
}

export default searchComponent;