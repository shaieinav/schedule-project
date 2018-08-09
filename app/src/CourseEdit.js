import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class CourseEdit extends Component {

    emptyItem = {
        courseNum: '',
        courseName: '',
        courseCredit: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.courseNum !== 'new') {
            const course = await (await fetch(`/courses/${this.props.match.params.courseNum}`)).json();
            this.setState({item: course});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        await fetch((item.courseNum)? '/courses/' + item.courseNum : '/courses', {
            method: (item.courseNum) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/courses');
    }

    render() {

        const {item} = this.state;
        const title = <h2>{item.courseNum ? 'Edit Group' : 'Add Group'}</h2>;

        return (
            <div>
                <AppNavbar/>
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="courseNum">Course Number</Label>
                            <Input type="text" name="courseNum" id="courseNum" value={item.courseNum || ''}
                                   onChange={this.handleChange} autoComplete="courseNum"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="courseName">Course Name</Label>
                            <Input type="text" name="courseName" id="courseName" value={item.courseName || ''}
                                   onChange={this.handleChange} autoComplete="courseName-level1"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="courseCredit">Course Credit</Label>
                            <Input type="text" name="courseCredit" id="courseCredit" value={item.courseCredit || ''}
                                   onChange={this.handleChange} autoComplete="courseCredit-level1"/>
                        </FormGroup>
                        <FormGroup>
                            <Button color="primary" type="submit">Save</Button>{' '}
                            <Button color="secondary" tag={Link} to="/courses">Cancel</Button>
                        </FormGroup>
                    </Form>
                </Container>
            </div>
        )
    }
}

export default withRouter(CourseEdit);