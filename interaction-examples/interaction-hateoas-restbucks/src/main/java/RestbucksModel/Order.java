package RestbucksModel;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="rb_order")
@SuppressWarnings("unused")
public class Order {

	@Id
	@Basic(optional = false)
	private String Id;

	private String location;
	private String name;
	private String email;
	private String milk;
	private Integer quantity;
	private String size;

	// an Order can have one Payments
	@OneToOne(cascade=CascadeType.ALL, mappedBy="order")
    private Payment payment;
	
	public Order() {
	}
}