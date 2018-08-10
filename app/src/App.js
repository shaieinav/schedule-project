import React, { Component } from 'react';
import './App.css';
import Home from './Components/Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import CourseList from './Components/CourseList';
import CourseEdit from './Components/CourseEdit';

class App extends Component {

    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/courses' exact={true} component={CourseList}/>
                    <Route path='/courses/:courseNum' component={CourseEdit}/>
                </Switch>
            </Router>
        )
    }
}

export default App;