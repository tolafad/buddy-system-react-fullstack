import React, { useState, useEffect } from 'react'
import UserService from '../services/UserService'
import { useParams } from "react-router-dom";

export const ViewUserComponent = () => {

	const [user, setUser] = useState({});

	let { id } = useParams();
 
	useEffect(() => {
		return UserService.getUserById(id).then((res) => {
			let newUser = res.data;
			let buddyname = (newUser.buddy && newUser.buddy.name) ? newUser.buddy.name : ''
			setUser({ name: newUser.name, email: newUser.email, buddy: buddyname });
		});
	}, id);


	return (
		<div>
			<h2 className="text-center">User Detail</h2>
			<div className="row">
				<button className="btn btn-primary"></button>
			</div>
			<br></br>
			<table className="table table-striped table-bordered">
				<tbody>
					<tr key={user.name}>
						<td> Name : </td>
						<td> {user.name} </td>
					</tr>
					<tr key={user.buddy}>
						<td> Prayer Buddy : </td>
						<td> {user.buddy} </td>
					</tr>

				</tbody>
			</table>
		</div>
	)

}

export default ViewUserComponent
