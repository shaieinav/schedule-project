import React, {Component} from 'react';
import FullCalendar from 'fullcalendar-reactwrapper';
import 'fullcalendar-reactwrapper/dist/css/fullcalendar.min.css';
import moment from 'moment';
import {getCurrentUser} from "../util/APIUtils";
import LoadingIndicator from "../common/LoadingIndicator";

class CalendarComponent extends Component {

    constructor(props) {

        super(props);

        this.state = {
            options: {
                header: {
                    left: '',
                    center: '',
                    right: ''
                },
                defaultView: 'agendaWeek',
                minTime: "08:00:00",
                maxTime: "23:00:00",
                allDaySlot: false,
                hiddenDays: [6],
                defaultDate: moment('2018-04-01'),
                columnFormat: 'ddd',
            },
            // courseName: 'temp',
            currentUserStudies: {},
            studentId: 0,
            eventsA: [],
            eventsB: [],
            eventsC: [],
            // loading: true,
            allCourses: {},
        };

        // this.getCourseName = this.getCourseName.bind(this);
    }

    // async getCourseName(courseNum) {
    //     await fetch(`http://localhost:8080/api/courses/${courseNum}`)
    //         .then(response => response.json())
    //         .then(data => this.setState({courseName: data.courseName}))
    //         .catch(error => {
    //             console.log('Error in determineCourseName');
    //         });
    // }

    async componentDidMount() {

        let updatedEventsA = [...this.state.eventsA], updatedEventsB = [...this.state.eventsB], updatedEventsC = [...this.state.eventsC];

        await getCurrentUser()
            .then(response => {
                this.setState({
                    studentId: response.studentId,
                });
            })
            .catch(error => {
                console.log('Error in componentDidMount while getting user information');
            });

        await fetch(`http://localhost:8080/studies/${this.state.studentId}`)
            .then(response => response.json())
            .then(userStudiesData => this.setState({
                currentUserStudies: userStudiesData,
            }))
            .catch(error => {
                console.log('Error in componentDidMount while getting user studies information');
            });

        await fetch(`http://localhost:8080/api/courses`)
            .then(response => response.json())
            .then(courseData => this.setState({
                allCourses: courseData,
            }))
            .catch(error => {
                console.log('Error in componentDidMount while getting courses');
            });

        const userCourses = this.props.currentUserCourses.map(course => {

            // fetch(`http://localhost:8080/api/courses/${course.enrolledId.courseNum}`)
            //     .then(response => response.json())
            //     .then(data => this.setState({
            //         courseName: data.courseName,
            //         loading: false
            //     }))
            //     .catch(error => {
            //         console.log('Error in componentDidMount:', error);
            //     });

            let name = this.state.allCourses.filter(courseOption => {
                return (JSON.stringify(courseOption.courseNum).toString() === JSON.stringify(course.enrolledId.courseNum).toString())
            });

            const colors = ['#FF8A80', '#FF80AB', '#EA80FC', '#B388FF', '#8C9EFF', '#82B1FF', '#80D8FF', '#84FFFF',
                '#A7FFEB', '#B9F6CA', '#CCFF90', '#F4FF81', '#FFFF8D', '#FFE57F', '#FFD180', '#FF9E80'];
            const color = colors[Math.floor(Math.random() * colors.length)];

            let day, time, event;

            // this.getCourseName(course.enrolledId.courseNum);

            let studies = this.state.currentUserStudies.filter(studies => {
                return (JSON.stringify(studies.studiesId.courseNum).toString() === JSON.stringify(course.enrolledId.courseNum).toString())
            });

            console.log("studies:", studies);

            let determineDay = function (day) {
                if (day === "א") return '2018-04-01';
                else if (day === "ב") return '2018-04-02';
                else if (day === "ג") return '2018-04-03';
                else if (day === "ד") return '2018-04-04';
                else if (day === "ה") return '2018-04-05';
                else if (day === "ו") return '2018-04-06';
            };

            // name = determineCourseName();

            day = determineDay(studies[0].studyDay);
            console.log("study day: " + studies[0].studyDay + ", determined day: " + day);
            time = studies[0].studyTime.split(['-']);
            // name = determineCourseName();
            console.log("course name in calendar", name);
            event = {
                title: `${name[0].courseName}
                ${course.enrolledId.courseNum}
                ${course.enrolledId.groupLocation}`,
                start: `${day}T${time[1]}`,
                end: `${day}T${time[0]}`,
                color: color,

            };

            // check the semester to decide if the event should be in semester A tab
            if(course.enrolledId.semesterName.indexOf('א') > -1) {
                updatedEventsA.push(event);
                this.setState({eventsA: updatedEventsA});
            }
            // check the semester to decide if the event should be in semester B tab
            else if(course.enrolledId.semesterName.indexOf('ב') > -1) {
                updatedEventsB.push(event);
                this.setState({eventsB: updatedEventsB});
                console.log("Semester B Events:", this.state.eventsB);
            }
            // check the semester to decide if the event should be in semester C tab
            else if(course.enrolledId.semesterName.indexOf('ג') > -1) {
                updatedEventsC.push(event);
                this.setState({eventsC: updatedEventsC});
            }
        });
    }

    async componentWillReceiveProps(nextProps) {

        if (JSON.stringify(this.props) === JSON.stringify(nextProps)) {
            return false;
        }
        this.props = nextProps;

        const colors = ['#FF8A80', '#FF80AB', '#EA80FC', '#B388FF', '#8C9EFF', '#82B1FF', '#80D8FF', '#84FFFF',
            '#A7FFEB', '#B9F6CA', '#CCFF90', '#F4FF81', '#FFFF8D', '#FFE57F', '#FFD180', '#FF9E80'];
        const color = colors[Math.floor(Math.random() * colors.length)];

        let name = '';
        await fetch(`http://localhost:8080/api/courses/${this.props.courseObject[0].courseGroupId.courseNum}`)
            .then(response => response.json())
            .then(data => name = data.courseName);

        let day, time, event, updatedEvents;
        let determineDay = function (day) {
            if (day === "א") return '2018-04-01';
            else if (day === "ב") return '2018-04-02';
            else if (day === "ג") return '2018-04-03';
            else if (day === "ד") return '2018-04-04';
            else if (day === "ה") return '2018-04-05';
            else if (day === "ו") return '2018-04-06';
        };
        console.log("course object", this.props.courseObject);
        console.log("course data name", this.state.courseName);
        day = determineDay(this.props.courseObject[0].day);
        time = this.props.courseObject[0].hours.split(['-']);
        event = {
            title: `${name}
                    ${this.props.courseObject[0].courseGroupId.courseNum}
                    ${this.props.courseObject[0].courseGroupId.groupLocation}`,
            start: `${day}T${time[1]}`,
            end: `${day}T${time[0]}`,
            color: color,
        };

        console.log(this.props.courseObject[0].courseGroupId.semesterName);

        // check the semester to decide if the event should be in semester A tab
        if(this.props.courseObject[0].courseGroupId.semesterName.indexOf('א') > -1) {
            updatedEvents = [...this.state.eventsA];
            updatedEvents.push(event);
            console.log(this.state.eventsA);
            this.setState({eventsA: updatedEvents});
        }
        // check the semester to decide if the event should be in semester B tab
        else if(this.props.courseObject[0].courseGroupId.semesterName.indexOf('ב') > -1) {
            updatedEvents = [...this.state.eventsB];
            updatedEvents.push(event);
            this.setState({eventsB: updatedEvents});
        }
        // check the semester to decide if the event should be in semester C tab
        else if(this.props.courseObject[0].courseGroupId.semesterName.indexOf('ג') > -1) {
            updatedEvents = [...this.state.eventsC];
            updatedEvents.push(event);
            this.setState({eventsC: updatedEvents});
        }

        return true;
    }

    handleClick = (calEvent, activeTabIndex) => {
        let deleteItem = window.confirm("Are you sure you want to delete this course?");
        if (deleteItem) {
            // check in what tab we are in and then updates the events list in this tab without the deleted course
            if (activeTabIndex === '1') {
                let updatedEvents = [...this.state.eventsA];
                updatedEvents = updatedEvents.filter(event => event.title !== calEvent.title)
                console.log(updatedEvents);
                this.setState({eventsA: updatedEvents});
            }
            else if (activeTabIndex === '2') {
                let updatedEvents = [...this.state.eventsB];
                updatedEvents = updatedEvents.filter(event => event.title !== calEvent.title)
                console.log(updatedEvents);
                this.setState({eventsB: updatedEvents});
            }
            else if (activeTabIndex === '3') {
                let updatedEvents = [...this.state.eventsC];
                updatedEvents = updatedEvents.filter(event => event.title !== calEvent.title)
                console.log(updatedEvents);
                this.setState({eventsC: updatedEvents});
            }
        }
    };

    render() {

        let {eventsA, eventsB, eventsC, options} = this.state;
        let semester;
        // check in which semester are we now and use the appropriate events list
        if (this.props.activeTabIndex === '1') semester = eventsA;
        else if (this.props.activeTabIndex === '2') semester = eventsB;
        else if (this.props.activeTabIndex === '3') semester = eventsC;

        return (
            <FullCalendar
                {...options}
                events={semester}
                eventClick={(calEvent) => this.handleClick(calEvent, this.props.activeTabIndex)}
            />
        )
    }
}

export default CalendarComponent;