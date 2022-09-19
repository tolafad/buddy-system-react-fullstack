import React, { useState, useEffect } from 'react'
import UserService from '../services/UserService'
import { useNavigate } from "react-router-dom";


export const ListUserComponent = () => {

	let navigate = useNavigate();
	const [id, setId] = useState(0);
	const [users, setUsers] = useState([]);

	useEffect(async () => {
		const res = await UserService.getUsers();
		setUsers(res.data);
	}, []);


	const viewUser = (id) => {
		navigate(`/view-user/${id}`);
	};

	const editUser = (id) => {
		navigate(`/update-user/${id}`);
	};
	
    const addUser = () => {
		navigate(`/add-user`);
	};

	const deleteUser = (id) => {
		UserService.deleteUser(id).then(res => {
			setUsers({ users: users.filter(user => user.id !== id) });
		});
		navigate(`/users`);
	}
	
	const generateOrViewBuddy = (id) => {
		UserService.generateBuddyById(id);
		navigate(`/users`);
	};

	return (
		<div>
			<h2 className="text-center">Users List</h2>
			<div className="row">
				<button className="btn btn-primary"></button>
			</div>
			<br></br>
			<div className="row">
				<button onClick={() => addUser()} className="btn btn-info">Add </button>

				<table className="table table-striped table-bordered">

					<thead>
						<tr>
							<th> User Name</th>
							<th> User Email</th>
							<th> Prayer Buddy</th>
							<th> Actions</th>
						</tr>
					</thead>
					<tbody>
						{
							users.map(
								user => (
									<tr key={user.id}>
										<td> {user.name} </td>
										<td> {user.email}</td>
										<td> {(user.buddy && user.buddy.name) ? user.buddy.name : ''}</td>
										<td>
											<button onClick={() => viewUser(user.id)} className="btn btn-info">View </button>
											<button style={{ marginLeft: "10px" }} onClick={() => generateOrViewBuddy(user.id)} className="btn btn-info">Generate Buddy </button>
											<button style={{ marginLeft: "10px" }} onClick={ () => editUser(user.id)} className="btn btn-info">Update </button>
											<button style={{ marginLeft: "10px" }} onClick={ () => deleteUser(user.id)} className="btn btn-danger">Delete </button>
										</td>
									</tr>
								)
							)
						}
					</tbody>
				</table>

			</div>

		</div>
	)


}

export default ListUserComponent
