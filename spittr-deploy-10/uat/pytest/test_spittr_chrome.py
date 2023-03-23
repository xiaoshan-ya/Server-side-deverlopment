# pytest test_spittr_chrome.py
import pytest
import allure
import time
import json
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities


# @pytest.mark.chrome
class TestSpittrChrome():
    def setup_method(self, method):
        option = webdriver.ChromeOptions()
        # 隐藏窗口
        option.add_argument('headless')
        # 防止打印一些无用的日志
        option.add_experimental_option("excludeSwitches", ['enable-automation', 'enable-logging'])
        self.driver = webdriver.Chrome(chrome_options=option)
        # self.driver = webdriver.Chrome()

        self.driver.implicitly_wait(30)
        self.vars = {}

    def teardown_method(self, method):
        self.driver.quit()

    @allure.story('test for chrome')
    @allure.description("""
    这是使用chrome做的测试.
    """)
    def test_add_spittle(self):
        self.driver.get("http://www.spittr.com/spittr/login")

        self.driver.find_element(By.NAME, "username").send_keys("tzs919")
        self.driver.find_element(By.NAME, "password").send_keys("123456")
        self.driver.find_element(By.NAME, "submit").click()

        # WebDriverWait(self.driver, 10).until(
        #     lambda d: d.find_element_by_link_text("Spittles").is_displayed())

        self.driver.find_element(By.LINK_TEXT, "Spittles").click()

        # WebDriverWait(self.driver, 10).until(
        #     lambda d: d.find_element_by_name("message").is_displayed())

        self.driver.find_element(By.NAME, "message").click()
        self.driver.find_element(By.NAME, "message").send_keys(
            "----" + time.strftime("%Y-%m-%d  %H:%M:%S", time.localtime()) + "----")

        self.driver.find_element(By.XPATH, "//input[@type='submit']").click()
        self.driver.find_element(By.XPATH, "//input[@type='submit']").click()

        self.driver.find_element(By.LINK_TEXT, "Logout").click()
        # self.driver.close()
#