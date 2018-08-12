import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import CourseList from './components/CourseList';
import CourseEdit from './components/CourseEdit';

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