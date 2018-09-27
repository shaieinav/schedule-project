import React from 'react';
import Select from 'react-select';
import {Container, Form, FormGroup, Label, Input, Button} from 'reactstrap';

const searchComponent = (props) => {

    const courseOptions = props.filteredCourses.map(course => {
        return {value: course.courseNum, label: course.courseName};
    });

    const semesterOptions = props.semestersList.map(semester => {
       return {value: semester.semesterName, label: semester.semesterName};
    });

    let groupOptions = props.selectedOption.map(group => {
        return {
            value: group.courseGroupId,
            label: (
                `Location: ${group.courseGroupId.groupLocation}, \n 
                Group Number: ${group.courseGroupId.groupNum}, \n
                Day: ${group.day}, \n
                Hours: ${group.hours}, \n
                Teaching Type: ${group.teachingType}\n`
            )
        }
    });

    let placeholder, disabled;
    if (props.selectedOption.length === 0) {
        placeholder = "No groups available";
        disabled = true;
    } else {
        placeholder = "Choose Group";
        disabled = false;
    }

    let groupPlaceholder;
    function func (key) {
        groupPlaceholder =  key;
        let temp = props.selectedOption.filter(option => {
                    return (JSON.stringify(option.courseGroupId).toString() === JSON.stringify(key).toString())
                })
        groupPlaceholder = temp;
        console.log('group place holder:\n' + groupPlaceholder);
    }

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
                            placeholder={placeholder}
                            isDisabled={disabled}
                            onChange={(value) => func(value.value)}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label for="courseNum">Filters</Label>
                        <Select
                            options={semesterOptions}
                            placeholder={'Filter By Semester'}
                        />
                    </FormGroup>
                    <Button color="danger" onClick={(event) => {
                        props.handleAddingCourseToCalendar(event, groupPlaceholder);
                    }}>Add Course</Button>
                </Form>
            </Container>
        </div>
    )
};

export default searchComponent;