<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form id="formRate">
    <div id="rating">
        <input type="radio" id="star5" ${rateNo==5?"checked":""} name="rating" value="5" />
        <label class="full" for="star5"  title="Awesome - 5 stars"></label>

        <input type="radio" id="star4" ${rateNo==4?"checked":""}  name="rating" value="4" />
        <label class="full" for="star4" title="Pretty good - 4 stars"></label>

        <input type="radio" id="star3" ${rateNo==3?"checked":""} name="rating" value="3" />
        <label class="full" for="star3" title="Meh - 3 stars"></label>

        <input type="radio" id="star2" ${rateNo==2?"checked":""} name="rating" value="2" />
        <label class="full" for="star2" title="Kinda bad - 2 stars"></label>

        <input type="radio" id="star1" ${rateNo==1?"checked":""} name="rating" value="1" />
        <label class="full" for="star1" title="Sucks big time - 1 star"></label>
    </div>
</form>